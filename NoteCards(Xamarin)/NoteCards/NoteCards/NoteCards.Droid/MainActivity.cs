using System;

using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;

namespace NoteCards.Droid
{
    [Activity(Label = "NoteCards.Droid", MainLauncher = true, Icon = "@drawable/icon")]
    public class MainActivity : Activity
    {
        private NoteBook _noteBook;
        private TextView _errorMsg;
        private TextView _pageTitle;
        private EditText _topicName;
        private Button _finishBtn;
        private Button _addBtn;
        private bool _isAddNewTopics;

        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);

            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.Main);

            _isAddNewTopics = Intent.GetBooleanExtra("createMoreTopics", false);
            _noteBook = NoteBook.getInstance();

            // Get view Id's
            _errorMsg = FindViewById<TextView>(Resource.Id.errorMsg);
            _pageTitle = FindViewById<TextView>(Resource.Id.topicTitle);
            _topicName = FindViewById<EditText>(Resource.Id.topicField);
            _finishBtn = FindViewById<Button>(Resource.Id.finishBtn);
            _addBtn = FindViewById<Button>(Resource.Id.addTopic);

            // Set view ID's
            _errorMsg.Text = string.Empty;

            if (_isAddNewTopics)
                _pageTitle.Text = "Add more topics!";

            _addBtn.Click += delegate
            {
                addTopic();
            };

            _finishBtn.Click += delegate
            {
                // If the intent has nothing in it, go to show card activity
                if (Intent != null)
                {
                    if (_noteBook.getTopicCount() != 0)
                    {
                        Intent x = new Intent(this, typeof(ShowCards));
                        StartActivity(x);
                    }
                    else
                    {
                        _errorMsg.SetTextColor(Android.Graphics.Color.Red);
                        _errorMsg.Text = "Add a topic before trying to continue!";
                    }
                }
                else
                    Finish();
            };
        }

        public void addTopic()
        {
            bool doesntExist = false;
            if (!_topicName.Text.Equals(string.Empty))
                doesntExist = _noteBook.createNewTopic(_topicName.Text);

            if (doesntExist)
            {
                _errorMsg.SetTextColor(Android.Graphics.Color.Green);
                _errorMsg.Text = "Topic added successfully";
                _topicName.Text = string.Empty;
            }
            else
            {
                _errorMsg.SetTextColor(Android.Graphics.Color.Red);
                if (_topicName.Text.Equals(string.Empty))
                    _errorMsg.Text = "* Add a title for your topic!";
                else
                    _errorMsg.Text = "* Topic has already been created. Please give it a different name!";
            }
        }
    }
}