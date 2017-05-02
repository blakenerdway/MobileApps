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
    class AddCard : Activity
    {
        private Topic _currentTopic;
        private Card _currentCard;
        private NoteBook _noteBook;
        private TextView _topicTitle;
        private EditText _newQuestion;
        private EditText _newAnswer;
        private Button _reset;
        private Button _addCard;
        private bool _isEditingCard;
        private int _currentCardIndex;
        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);

            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.addCard);

            _noteBook = NoteBook.getInstance();
            _currentTopic = _noteBook.getTopic(Intent.GetStringExtra("TopicName"));
            _isEditingCard = Intent.GetBooleanExtra("Edit Card", false);
            // Pass the index so it knows which question to set text to
            _currentCardIndex = Intent.GetIntExtra("QuestionName", 0);

            _addCard = FindViewById<Button>(Resource.Id.addNewCard);
            _reset = FindViewById<Button>(Resource.Id.reset);
            _newAnswer = FindViewById<EditText>(Resource.Id.answerText);
            _newQuestion = FindViewById<EditText>(Resource.Id.questionText);
            _topicTitle = FindViewById<TextView>(Resource.Id.topicTitle);

            if (_isEditingCard)
            {
                _currentCard = _currentTopic.getAllCards()[_currentCardIndex];
                _topicTitle.Text = "Edit Card!";
                _newQuestion.Text = _currentCard.getQuestion();
                _newAnswer.Text = _currentCard.getAnswer();
                _addCard.Text = "Save change";
            }
            else
                _topicTitle.Text = "Add new cards for topic: \n" + _currentTopic.getName();

            // Reset the text
            _reset.Click += delegate
            {
                _newAnswer.Text = string.Empty;
                _newQuestion.Text = string.Empty;
            };

            // Add the card to the topic
            _addCard.Click += delegate
            {
                if (_newQuestion.Text.Length > 0 && !_newQuestion.Text.Equals("New Question"))
                {
                    if (_newAnswer.Text.Length > 0 && !_newAnswer.Text.Equals("New Answer"))
                    {                        
                        if (_isEditingCard)
                        {
                            _currentTopic.removeCard(_currentCard);
                            Toast.MakeText(this, "Changes made successfully!", ToastLength.Short).Show();
                            System.Threading.Thread.Sleep(1500);
                            Finish();
                        }

                        else
                            Toast.MakeText(this, "Card added successfully!", ToastLength.Short).Show();

                        _noteBook.createNewCard(_currentTopic.getName(), _newQuestion.Text, _newAnswer.Text);
                        _newAnswer.Text = string.Empty;
                        _newQuestion.Text = string.Empty;
                    }
                }
            };
        }
    }
}