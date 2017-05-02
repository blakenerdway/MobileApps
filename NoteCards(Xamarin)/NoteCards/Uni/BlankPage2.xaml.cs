using NoteCards;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using System.Windows.Input;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using Windows.UI.Core;
using Windows.System;
using System.Threading;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234238

namespace Uni
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class BlankPage2 : Page
    {
        private bool isFirstTime = false;
        public BlankPage2()
        {
            this.InitializeComponent();
            firstTopic.GotFocus += (source, e) =>
            {
                if (firstTopic.Text.Equals("Enter your first topic here!"))
                {
                    firstTopic.SelectionStart = 0;
                    firstTopic.Text = string.Empty;
                }
            };

            firstTopic.LostFocus += (source, e) =>
            {
                if (firstTopic.Text.Length == 0)
                {
                    firstTopic.Text = "Enter your first topic here!";
                }
            };
        }


        protected override void OnNavigatedTo(NavigationEventArgs e) //OnNavigatedTo is the event that fires when activity started
        {
            base.OnNavigatedTo(e);
            isFirstTime = (bool)e.Parameter;
            if (!isFirstTime)
            {
                topicBox.Text = "Create more topics!";
                addFirstTopicBtn.Content = "Add";

                // Create a back button
                SystemNavigationManager.GetForCurrentView().AppViewBackButtonVisibility =
                AppViewBackButtonVisibility.Visible;

                // Subscribe an action to when the back button is pressed
                SystemNavigationManager.GetForCurrentView().BackRequested += (source, f) =>
                {
                    if (Frame.CanGoBack)
                        Frame.GoBack();
                };
            }
        }

        private void addFirstTopicBtn_Click(object sender, RoutedEventArgs e)
        {
            addTopic();
        }

        private void firstTopic_KeyDown(object sender, KeyRoutedEventArgs e)
        {
            if (firstTopic.Text.Equals("Enter your first topic here!"))
                firstTopic.Text = string.Empty;

            if (e.Key.Equals(VirtualKey.Enter))
                addTopic();

        }

        // Add the topic to the notebook
        public void addTopic()
        {
            bool canAdd = true; // Assume that the topic can be added, until it can't

            errorMessage.Text = string.Empty;
            // Add the topic if the text field is blank
            if (!firstTopic.Text.Equals("Enter your first topic here!") && !firstTopic.Text.Equals(string.Empty))
                canAdd = NoteBook.getInstance().createNewTopic(firstTopic.Text);

            if (isFirstTime)
            {
                if (Frame.CanGoBack)
                    Frame.GoBack();
            }
            else
            {
                if (canAdd)
                {
                    firstTopic.Text = string.Empty;
                    firstTopic.Focus(FocusState.Keyboard);
                }
                else
                {

                    errorMessage.Text = "* Topic " + firstTopic.Text + " was created previously, try adding a different topic";
                }

            }
        }
    }
}