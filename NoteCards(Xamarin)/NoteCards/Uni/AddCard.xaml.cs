using NoteCards;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using transfer;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.System;
using Windows.UI.Core;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234238

namespace Uni
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class BlankPage1 : Page
    {
        private NoteBook _noteBook;
        private Topic _currentTopic;

        //private Popup errorDialog;

        // add a back arrow/title to this page!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        public BlankPage1()
        {
            this.InitializeComponent();
            _noteBook = NoteBook.getInstance();
            //errorDialog = new Popup();
            //TextBlock errorText = new TextBlock();
            //errorText.IsTapEnabled = false;
            //errorText.Text = "Please enter values for both question and answers!";
            //errorDialog.Child = errorText;



            // Make these actual functions because it's being done multiple times
            addQuestion.GotFocus += (source, e) =>
            {
                if (addQuestion.Text.Equals("New Question"))
                    addQuestion.Text = string.Empty;
            };

            addQuestion.LostFocus += (source, e) =>
            {
                if (addQuestion.Text.Length == 0)
                    addQuestion.Text = "New Question";
            };

            addAnswer.GotFocus += (source, e) =>
            {
                if (addAnswer.Text.Equals("New Answer"))
                    addAnswer.Text = string.Empty;
            };

            addAnswer.LostFocus += (source, e) =>
            {
                if (addAnswer.Text.Length == 0)
                    addAnswer.Text = "New Answer";
            };

            // Create a back button
            SystemNavigationManager.GetForCurrentView().AppViewBackButtonVisibility =
            AppViewBackButtonVisibility.Visible;

            // Subscribe an action to when the back button is pressed
            SystemNavigationManager.GetForCurrentView().BackRequested += (source, e) =>
            {
                if (Frame.CanGoBack)
                    Frame.GoBack();
            };

        }

        protected override void OnNavigatedTo(NavigationEventArgs e) //OnNavigatedTo is the event that fires when activity started
        {
            base.OnNavigatedTo(e);
            _currentTopic = (Topic) e.Parameter;
            topicName.Text = "Add new cards for: " + _currentTopic.getName();
        }

        private void reset_Click(object sender, RoutedEventArgs e)
        {
            addQuestion.Text = "New Question";
            addAnswer.Text = "New Answer";
        }

        private void add_Click(object sender, RoutedEventArgs e)
        {
            addQuestionAnswer();
        }

        private void addQuestion_KeyDown(object sender, KeyRoutedEventArgs e)
        {
            if (e.Key.Equals(VirtualKey.Enter))
            {
                addQuestionAnswer();
            }
        }

        private void addAnswer_KeyDown(object sender, KeyRoutedEventArgs e)
        {
            if (e.Key.Equals(VirtualKey.Enter))
            {
                addQuestionAnswer();
            }         
        }

        private void addQuestionAnswer()
        {
            if (addQuestion.Text.Length > 0 && !addQuestion.Text.Equals("New Question"))
            {
                if (addAnswer.Text.Length > 0 && !addAnswer.Text.Equals("New Answer"))
                {
                    _noteBook.createNewCard(_currentTopic.getName(), addQuestion.Text, addAnswer.Text);
                    addQuestion.Text = "New Question";
                    addAnswer.Text = "New Answer";
                    addQuestion.Focus(FocusState.Keyboard);
                }
            }
        }
    }
}
