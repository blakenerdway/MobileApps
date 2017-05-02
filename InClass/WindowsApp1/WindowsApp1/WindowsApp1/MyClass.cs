using System;

namespace WindowsApp1
{
    public class MyClass
    {
        private int count;
        public MyClass()
        {
            count = 0;
        }

        public void incrementCount()
        {
            count++;
        }

        public string showCount()
        {
            return string.Format("{0} button clicks", count);
        }
    }
}

