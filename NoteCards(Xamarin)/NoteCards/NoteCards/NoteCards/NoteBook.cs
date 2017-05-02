using System;
using System.Collections.Generic;
using System.Linq;
using transfer;

namespace NoteCards
{
    public class NoteBook
    {
        private static NoteBook _instance;
        private List<Topic> _topics;
        private List<string> _topicNames;

        private int _currentTopic; // Used to keep track of which topic is being used currently

        public static NoteBook getInstance()
        {
            if (_instance == null)
                _instance = new NoteBook();

            return _instance;
        }

        //Constructor, create a new list of topics
        private NoteBook()
        {
            _topics = new List<Topic>();
            _topicNames = new List<string>();
            _topicNames.Add("-----------------");
            _topicNames.Add("+ Add new topic!");

            _currentTopic = 0;
        }

        // Get which index is being used
        public int getCurrentTopicNum()
        {
            return _currentTopic;
        }

        // Set the current index
        public void setCurrentTopicNum(int index)
        {
            _currentTopic = index;
        }

        // Create a new card for the topic
        public void createNewCard(string topicName, string question, string answer)
        {
            Card newCard = new Card(question, answer);

            // Add the card to the topic
            foreach (Topic t in _topics)
            {
                if (t.getName().Equals(topicName))
                    _topics[_topics.IndexOf(t)].addCard(newCard);

            }
        }

        // Create a new topic with a certain name
        public bool createNewTopic(string name)
        {
            foreach (string s in _topicNames)
            {
                if (s.Equals(name))
                    return false;
            }
            // Remove the dash and the add new topic strings
            _topicNames.RemoveAt(_topicNames.Count - 1);
            _topicNames.RemoveAt(_topicNames.Count - 1);
            _topics.Add(new Topic(name));
            _topicNames.Add(name);
            _topicNames = _topicNames.OrderBy(x => x).ToList();
            _topicNames.Add("-----------------");
            _topicNames.Add("+ Add new topic!");
            return true;
        }


        //Return cards based on the topic that is selected
        public Topic getTopic(string tName)
        {
            foreach (Topic t in _topics)
                if (t.getName().Equals(tName))
                    return t;

            return null;
        }

        // Return all the topic names
        public List<string> getTopicNames()
        {
            return _topicNames;
        }

        // Return the number of topics
        // Because I am too lazy to type getTopics().Count 4 times;
        public int getTopicCount()
        {
            return _topics.Count;
        }
    }
}