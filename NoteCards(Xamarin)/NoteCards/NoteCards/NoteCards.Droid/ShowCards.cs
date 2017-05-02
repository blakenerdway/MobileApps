using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using transfer;

namespace NoteCards.Droid
{
    [Activity]
    class ShowCards : Activity
    {
        private NoteBook _noteBook;
        private TextView _questionanswer;
        private TextView _date;
        private Button _editCard;
        private Button _removeCard;
        private Button _addCard;
        private Button _nextBtn;
        private Button _prevBtn;
        private Spinner _topicChooser;
        private Topic _currentTopic;
        private ArrayAdapter<string> spinnerAdapter;
        private List<Card> _cardsToShow;
        private int _previousTopicIndex;
        private int _currentCard;
        private bool _questionShowing;

        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);

            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.showCards);

            _noteBook = NoteBook.getInstance();
            _questionShowing = true;
            _cardsToShow = new List<Card>();

            _questionanswer = FindViewById<TextView>(Resource.Id.questionAnswer);
            _date = FindViewById<TextView>(Resource.Id.date);
            _editCard = FindViewById<Button>(Resource.Id.editCard);
            _removeCard = FindViewById<Button>(Resource.Id.removeCard);
            _nextBtn = FindViewById<Button>(Resource.Id.nextBtn);
            _prevBtn = FindViewById<Button>(Resource.Id.prevBtn);
            _addCard = FindViewById<Button>(Resource.Id.addCardBtn);
            _topicChooser = FindViewById<Spinner>(Resource.Id.topicChooser);

            _date.Text = string.Empty;

            _topicChooser.ItemSelected += new EventHandler<AdapterView.ItemSelectedEventArgs>(spinner_ItemSelected);
            spinnerAdapter = new ArrayAdapter<string>(this, Android.Resource.Layout.SimpleSpinnerItem, _noteBook.getTopicNames());
            _topicChooser.Adapter = spinnerAdapter;


            if (_noteBook.getTopicCount() != 0)
            {
                _topicChooser.SetSelection(_noteBook.getCurrentTopicNum());
                _previousTopicIndex = 0;
                _currentTopic = _noteBook.getTopic((string)_topicChooser.SelectedItem);
                _cardsToShow = _currentTopic.getAllCards();
                _currentCard = _cardsToShow.Count - 1;
            }

            if (_currentTopic != null)
                changeCards();


            _removeCard.Click += delegate
            {
                if (_cardsToShow.Count != 0)
                {
                    _cardsToShow.RemoveAt(_currentCard);
                    _currentCard = _currentCard - 1;
                    if (_currentCard == -1)
                        _currentCard = 0;
                }
                showQuestion();
            };

            _addCard.Click += delegate
            {
                Intent x = new Intent(this, typeof(AddCard));
                x.PutExtra("TopicName", (string)_topicChooser.SelectedItem);
                StartActivity(x);
            };

            _editCard.Click += delegate
            {
                if (_cardsToShow.Count != 0)
                {
                    Intent x = new Intent(this, typeof(AddCard));
                    x.PutExtra("Edit Card", true);
                    // Pass the index so it knows which question to set text to
                    x.PutExtra("QuestionName", _currentCard);
                    x.PutExtra("TopicName", (string)_topicChooser.SelectedItem);
                    StartActivity(x);
                }
            };

            _questionanswer.Click += delegate
            {
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
            };

            // Go to next card
            _nextBtn.Click += delegate
            {
                _editCard.Text = "Edit card";
                if (_cardsToShow.Count != 0)
                    _currentCard = (_currentCard + 1) % _cardsToShow.Count;
                showQuestion();
            };

            // Go to previous card
            _prevBtn.Click += delegate
            {
                if (_cardsToShow.Count != 0)
                    _currentCard = (_currentCard == 0) ? (_cardsToShow.Count - 1) : _currentCard - 1;
                showQuestion();
            };
        }

        protected override void OnResume()
        {
            base.OnResume();
            changeCards();
        }

        private void spinner_ItemSelected(object sender, AdapterView.ItemSelectedEventArgs e)
        {
            _currentCard = 0;
            changeCards();
        }

        public void changeCards()
        {
            if (_currentTopic != null)
            {
                // If same topic has been chosen, don't do anything
                if (_topicChooser.SelectedItemPosition != _noteBook.getTopicNames().Count - 1 &&
                    _topicChooser.SelectedItemPosition != _noteBook.getTopicNames().Count - 2)
                {
                    _noteBook.setCurrentTopicNum(_topicChooser.SelectedItemPosition);
                    _currentTopic = _noteBook.getTopic((string)_topicChooser.SelectedItem);
                    _cardsToShow = _currentTopic.getAllCards();
                    _previousTopicIndex = _topicChooser.SelectedItemPosition;
                    showQuestion();

                }
                // If add topic
                else if (_topicChooser.SelectedItemPosition == _noteBook.getTopicNames().Count - 1)
                {
                    Intent x = new Intent(this, typeof(MainActivity));
                    x.PutExtra("createMoreTopics", true);
                    StartActivity(x);
                }
                // If the dashed line was clicked
                else if (_topicChooser.SelectedItemPosition == _noteBook.getTopicNames().Count - 2)
                {
                    _topicChooser.SetSelection(_previousTopicIndex);
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
                    _questionanswer.Text = _cardsToShow[_currentCard].getQuestion();
                    _date.Text = "Created: " + created.Month + "/" + created.Day + "/" + created.Year;
                }
            }
            else
            {
                _questionanswer.Text = "No cards in deck, add some by pressing the add card button!";
                _date.Text = string.Empty;
            }
        }

        public void showAnswer()
        {
            if (_cardsToShow.Count != 0)
                if (_cardsToShow[_currentCard].getAnswer() != null)
                {
                    _questionShowing = false;
                    _questionanswer.Text = _cardsToShow[_currentCard].getAnswer();
                }
        }
    }
}