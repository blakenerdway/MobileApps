using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace transfer
{
    public class Card
    {
        private string _question;
        private string _answer;
        private DateTime _date;

        public Card(string q, string a)
        {
            _question = q;
            _answer = a;
            _date = DateTime.Today;
        }

        public string getQuestion()
        {
            return _question;
        }

        public string getAnswer()
        {
            return _answer;
        }

        public void setQuestion(string q)
        {
            _question = q;
        }

        public void setAnswer(string a)
        {
            _answer = a;
        }

        public DateTime getDateCreated()
        {
            return _date;
        }

    }
}
