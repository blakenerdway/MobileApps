using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using NoteCards;
using transfer;
using Windows.UI.Core;
using Windows.UI;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=402352&clcid=0x409

namespace Uni
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {
        private NoteBook _noteBook;
        private Topic _currentTopic;
        private List<Card> _cardsToShow;
        private int _currentCard;
        private bool _questionShowing;
        private int _previousIndex;

        public MainPage()
        {
            this.InitializeComponent();

            _noteBook = NoteBook.getInstance();
            _cardsToShow = new List<Card>();

            if (SystemNavigationManager.GetForCurrentView().AppViewBackButtonVisibility == AppViewBackButtonVisibility.Visible)
                SystemNavigationManager.GetForCurrentView().AppViewBackButtonVisibility = AppViewBackButtonVisibility.Collapsed;

            _questionShowing = true;
            // Do stuff here to set the dropdownList values etc
            topicChooser.ItemsSource = _noteBook.getTopicNames();

            if (_noteBook.getTopicCount() != 0)
            {
                topicChooser.SelectedIndex = _noteBook.getCurrentTopicNum();
                _previousIndex = 0;
                _currentTopic = _noteBook.getTopic((string)topicChooser.SelectedItem);
                _cardsToShow = _currentTopic.getAllCards();
                _currentCard = _cardsToShow.Count - 1;
            }

            if (_currentTopic != null)
                changeCards();

            // Add a subscriber to the loaded action for whenever the app loads
            Loaded += initializeScreen;
        }

        // If no topic has been created yet, add a new activity
        public void initializeScreen(object sender, RoutedEventArgs e)
        {
            Loaded -= initializeScreen;
            // If no topics created
            if (_noteBook.getTopicCount() == 0)
                this.Frame.Navigate(typeof(BlankPage2), true);
            else if (_cardsToShow.Count != 0)
            {
                DateTime created = _cardsToShow[_currentCard].getDateCreated();
                title.Text = "Question";
                questionanswer.Text = _cardsToShow[_currentCard].getQuestion();
                dateText.Text = "Created" + created.Month + "/" + created.Day + "/" + created.Year;
            }
        }

        // Swap between topics
        public void changeCards()
        {
            if (_currentTopic != null)
            {
                // If same topic has been chosen, don't do anything
                if (topicChooser.SelectedIndex != _noteBook.getTopicNames().Count - 1 &&
                    topicChooser.SelectedIndex != _noteBook.getTopicNames().Count - 2)
                {
                    if (!_currentTopic.Equals(_noteBook.getTopic((string)topicChooser.SelectedItem)))
                    {
                        _noteBook.setCurrentTopicNum(topicChooser.SelectedIndex);
                        _currentTopic = _noteBook.getTopic((string)topicChooser.SelectedItem);
                        _cardsToShow = _currentTopic.getAllCards();
                        _previousIndex = topicChooser.SelectedIndex;
                        showQuestion();
                    }
                }
                // If add topic
                else if (topicChooser.SelectedIndex == _noteBook.getTopicNames().Count - 1)
                {
                    this.Frame.Navigate(typeof(BlankPage2), false);
                }
                // If the dashed line was clicked
                else if (topicChooser.SelectedIndex == _noteBook.getTopicNames().Count - 2)
                {
                    topicChooser.SelectedIndex = _previousIndex;
                }
            }
        }

        public void showQuestion()
        {
            if (_cardsToShow.Count != 0)
            {
                if (_cardsToShow[_currentCard].getQuestion() != null)
                {
                    _questionShowing = true;
                    DateTime created = _cardsToShow[_currentCard].getDateCreated();
                    questionanswer.Text = _cardsToShow[_currentCard].getQuestion();
                    title.Text = "Question";
                    dateText.Text = "Created: " + created.Month + "/" + created.Day + "/" + created.Year;
                }
            }
            else
            {
                title.Text = string.Empty;
                questionanswer.Text = "No cards in deck, add some by pressing the add card button!";
                dateText.Text = string.Empty;
            }
        }

        public void showAnswer()
        {
            if (_cardsToShow.Count != 0)
                if (_cardsToShow[_currentCard].getAnswer() != null)
                {
                    _questionShowing = false;
                    title.Text = "Answer";
                    questionanswer.Text = _cardsToShow[_currentCard].getAnswer();
                }
        }

        // New topic gets chosen
        private void topicChooser_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            _currentCard = 0;
            changeCards();
        }

        // Previous button click
        private void prev_Click(object sender, RoutedEventArgs e)
        {
            edit.Content = "Edit card";
            questionanswer.IsTapEnabled = false;
            questionanswer.IsHitTestVisible = false;
            if (_cardsToShow.Count != 0)
                _currentCard = (_currentCard == 0) ? (_cardsToShow.Count - 1) : _currentCard - 1;
            showQuestion();
        }

        // Next button click
        private void next_Click(object sender, RoutedEventArgs e)
        {
            edit.Content = "Edit card";
            questionanswer.IsTapEnabled = false;
            questionanswer.IsHitTestVisible = false;
            if (_cardsToShow.Count != 0)
                _currentCard = (_currentCard + 1) % _cardsToShow.Count;
            showQuestion();
        }

        // Add a new card to the topic
        private void add_Click(object sender, RoutedEventArgs e)
        {
            this.Frame.Navigate(typeof(BlankPage1), _currentTopic);
        }

        private void questionanswer_Tapped(object sender, TappedRoutedEventArgs e)
        {
            // Show answer if showing question
            if (_questionShowing)
            {
                _questionShowing = false;
                showAnswer();
            }

            // Show question if showing answer
            else
            {
                _questionShowing = true;
                showQuestion();
            }
        }

        private void edit_Click(object sender, RoutedEventArgs e)
        {
            if (_cardsToShow.Count != 0)
            {
                if (edit.Content.Equals("Edit card"))
                {
                    edit.Content = "Done";
                    questionanswer.IsTapEnabled = true;
                    questionanswer.IsHitTestVisible = true;
                    questionanswer.Focus(FocusState.Keyboard);
                }
                else
                {
                    edit.Content = "Edit card";
                    if (title.Text.Equals("Question"))
                        _cardsToShow[_currentCard].setQuestion(questionanswer.Text);
                    else
                        _cardsToShow[_currentCard].setAnswer(questionanswer.Text);

                    questionanswer.IsTapEnabled = false;
                    questionanswer.IsHitTestVisible = false;
                }
            }
        }

        private void remove_Click(object sender, RoutedEventArgs e)
        {
            if (_cardsToShow.Count != 0)
            {
                _cardsToShow.RemoveAt(_currentCard);
                _currentCard = _currentCard - 1;
                if (_currentCard == -1)
                    _currentCard = 0;
            }
            showQuestion();
        }
    }
}
