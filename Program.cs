namespace TestJunk
{
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Linq;
    using System.Text;

    class Program
    {
        static void Main(string[] args)
        {
            int n = 2;
            PrintBinaryRep(n);
            PrintBinaryRep(32);
            PrintBinaryRep(65);
            PrintBinaryRep(3);


            string str = ItoS(1000);
            str = ItoS(123);
            str = ItoS(-123);

            string s = "this is a test of the emergency broadcast system";
            string r = "si";

            string removed = RemoveChars(s, r);
            string r2 = RemoveSpecified(s, r);


            int i = AtoI("123");
            i = AtoI("w1w");
            i = AtoI("1");
            i = AtoI("100");
            i = AtoI("0");
            i = AtoI("9");


            Console.WriteLine(FindFirstIndex("abcd", "bc"));
            Console.WriteLine(FindFirstIndex("abcd", "d"));
            Console.WriteLine(FindFirstIndex("abcd", "cd"));
            Console.WriteLine(FindFirstIndex("abcd", "ab"));
            Console.WriteLine(FindFirstIndex("abcd", "a"));
            Console.WriteLine(FindFirstIndex("abcd", "e"));

            r = reverse("this is a test");
            r = reverse(" a test is this");
            r = reverse("a test with a char to start");
            int[] input0 = new int[] {3,5,6,1,2,3};

            MergeSort mergeSort = new MergeSort();
            List<int> unordered = new List<int>(new[] {1,5,69,3,90,3124,3,90,345,8,0,7,99});
            Console.Write("Input list: ");
            unordered.ForEach(PrintIt);

            mergeSort.Sort(ref unordered);

            Console.Write("Sorted List: ");
            unordered.ForEach(Console.Write);
            Console.WriteLine();

            string input = "abc 123 def-xyz 652 sdfv";
            string output = new string(input.Where(Char.IsDigit).ToArray());
            Console.WriteLine(output);
        }

        public static void PrintBinaryRep(int i)
        {
            Stack s = new Stack();
            while (i > 0)
            {
                if (i % 2 > 0) s.Push(1);
                else s.Push(0);
                i = i >> 1;
            }
            while (s.Count > 0)
            {
                Console.Write(s.Pop());
            }
            Console.WriteLine();
        }

        public static string reverse(string words)
        {
            StringBuilder sb = new StringBuilder(words.Length);

            int bw = words.ToArray().GetUpperBound(0);

            while (words.Length > 0)
            {
                while (bw >= 0 && isWhite(words[bw]))
                {
                    bw--;
                }
                sb.Append(words.Substring(bw + 1, words.Length - (bw + 1)));
                words = words.Substring(0, bw + 1);

                while (bw >= 0 && !isWhite(words[bw]))
                {
                    bw--;
                }
                sb.Append(words.Substring(bw + 1, words.Length - (bw + 1)));
                words = words.Substring(0, bw + 1);

            }

            return sb.ToString();

        }

        public static bool isWhite(char c)
        {
            return c.ToString().IndexOfAny(new char[] {' '}) >= 0;
        }

        private static void PrintIt(int obj)
        {
            Console.Write(obj + ", ");
        }


        public static void LinqTest()
        {
            string[] names = { "Burke", "Connor", "Frank", 
                       "Everett", "Albert", "George", 
                       "Harris", "David" };

            IEnumerable<string> query = from s in names
                                        where s.Length == 5
                                        orderby s
                                        select s.ToUpper();

            foreach (string item in query)
                Console.WriteLine(item);
        }

        public static int AtoI(string s) 
        {
            int sum = 0;
            int factor = 10;
            bool negative = false;
            if (s.StartsWith("-"))
            {
                negative = true;
                s = s.Substring(1);
            }
            foreach (char c in s) 
            {
                sum *= factor;
                int i = Convert(c);     // i = c - '0';
                sum += i;
            }

            return negative ? sum * -1 : sum;
        }

        public static string ItoS(int i)
        {
            bool neg = i < 0;
            if (neg) 
                i *= -1;

            StringBuilder sb = new StringBuilder();
            Stack<char> s = new Stack<char>();

            while (i > 0)
            {
                s.Push((char)((i % 10) + '0'));
                i /= 10;
            }

            if (neg) s.Push('-');

            while (s.Count > 0)
                sb.Append(s.Pop());

            return sb.ToString();
        }

        public static int Convert(char c)
        {
            int i = c - '0';

            if (i < 0 || i > 9) throw new ArgumentOutOfRangeException(string.Format("Unable to convert {0} to int", c));
            return i;
        }

        public static int FindFirstIndex(string src, string sub)
        {
            int i = 0;
            foreach (char c in src)
            {
                if (c == sub[0])
                {
                    int j = i;
                    bool match = true;
                    foreach (char ch in sub)
                    {
                        if (ch != src[j++])
                        {
                            match = false;
                            break;
                        }
                    }
                    if (match) return i;
                }
                i++;
            }

            return -1;
        }

        public static int FindIndex(string src, string sub) 
        {
            int i = 0;
            while (i <= (src.Length  - sub.Length) )
            {
                if (IsEqual(SubStr(src, i, i + sub.Length - 1), sub))
                {
                    return i;
                }

                i++;
            }

            return -1;
        }

        public static bool IsEqual(string s1, string s2) 
        {
            int i = 0;
            if (s1.Length != s2.Length)
                return false;
            while (i < s1.Length && s1[i] == s2[i])
                i++;
            return i == s1.Length && i == s2.Length;
        }

        public static string SubStr(string src, int bi, int ei)
        {
            StringBuilder sb = new StringBuilder(ei - bi + 1);
            for (int i = bi; i <= ei; i++) 
            {
                sb.Append(src[i]);
            }

            return sb.ToString();
        }

        public static string RemoveChars(string src, string rem)
        {
            char[] s = src.ToCharArray();
            char[] r = rem.ToCharArray();
            bool[] flags = new bool[128];       // only handling ASCII chars
            int len = s.Length;
            int si, di;

            for (si = 0; si < r.Length; ++si)
            {
                flags[r[si]] = true;
            }

            si = 0; di = 0;

            while (si < s.Length)
            {
                if (!flags[(int)s[si]])
                {
                    s[di++] = s[si];
                }
                ++si;
            }

            return new string(s, 0, di);
        }

        public static string RemoveSpecified(string src, string remove)
        {
            StringBuilder sb = new StringBuilder();

            HashSet<char> hs = new HashSet<char>();
            foreach (char c in remove.ToCharArray())
            {
                hs.Add(c);
            }

            foreach (char c in src.ToCharArray())
            {
                if (!hs.Contains(c))
                {
                    sb.Append(c);
                }
            }

            return sb.ToString();
        }

    }

    interface IMyStack
    {
        void push(Object obj);
        Object pop();
    }

    class MyStack : IMyStack
    {
        private ArrayList list = new ArrayList();

        public MyStack(ArrayList list)
        {
            this.list = list;
        }

        public void push(object obj)
        {
            list.Add(obj);
        }

        public object pop()
        {
            object obj = list[list.Count - 1];
            list.RemoveAt(list.Count - 1);
            // list.TrimToSize(); //?

            return obj;
        }
    }

    interface IMyStack2 : IMyStack
    {
        Object peek();
    }

    class PeekTest : IMyStack2
    {
        private ArrayList list;
        private IMyStack stack;

        public PeekTest(ArrayList list)
        {
            this.list = list;
            stack = new MyStack(list);
        }

        public void push(object obj)
        {
            stack.push(obj);
        }

        public object pop()
        {
            return stack.pop();
        }

        public object peek()
        {
            return list[list.Count - 1];
        }
    }

    public class MergeSort
    {
        public void Sort(ref List<int> list)
        {
            if (list.Count <= 1)
            {
                return;
            }

            List<int> left = list.GetRange(0, list.Count/2);
            List<int> right = list.GetRange(list.Count/2, list.Count - list.Count/2);

            Sort(ref left);
            Sort(ref right);

            list = Sort(left, right);
        }

        public List<int> Sort(List<int> firstHalf, List<int> lastHalf)
        {
            List<int> sortedList = new List<int>(firstHalf.Count + lastHalf.Count);

            int index1 = 0;
            int index2 = 0;

            while (index1 < firstHalf.Count && index2 < lastHalf.Count)
            {
                if (firstHalf[index1] < lastHalf[index2])
                {
                    sortedList.Add(firstHalf[index1++]);
                }
                else
                {
                    sortedList.Add(lastHalf[index2++]);
                }
            }

            while (index1 < firstHalf.Count)
            {
                sortedList.Add(firstHalf[index1++]);
            }

            while (index2 < lastHalf.Count)
            {
                sortedList.Add(lastHalf[index2++]);
            }

            return sortedList;
        }
    }

}
