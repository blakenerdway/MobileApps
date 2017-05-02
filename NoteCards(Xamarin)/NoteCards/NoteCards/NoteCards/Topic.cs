using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace transfer
{
   public class Topic
   {
      private List<Card> _noteCards;
      private string _topicName;

      public Topic(string name)
      {
         _noteCards = new List<Card>();
         _topicName = name;
      }

      // Add a card to the list
      public void addCard(Card note)
      {
         _noteCards.Add(note);
      }

      public List<Card> getAllCards()
      {
         return _noteCards;
      }


      // Remove a card from the list
      public bool removeCard(Card note)
      {
         foreach (Card n in _noteCards)
         {
            if (n.Equals(note))
            {
               _noteCards.Remove(note);
               return true;
            }
         }
         return false;
      }

      public void changeName(string newName)
      {
         _topicName = newName;
      }

      public string getName()
      {
         return _topicName;
      }
   }
}
