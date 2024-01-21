using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.RightsManagement;
using System.Text;
using System.Threading.Tasks;

namespace ExampleMessageBox
{
    public class Person
    {
        public Person(string name, string firstName, string birthDay ="1/1/2001")
        {
            Name = name;
            FirstName = firstName;
            BirthDay = DateTime.Parse(birthDay);
        }

        public string Name { get; set; }
        public string FirstName { get; set; }
        
        public DateTime BirthDay { get; set; }

        public int Age { get => ComputeAge(); }

        private int ComputeAge()
        {
            int age = DateTime.Now.Year - BirthDay.Year;
            if (DateTime.Now < BirthDay.AddYears(age))
            {
                age--;
            }
            return age;
        }

        public override string ToString()
        {
            return $"{FirstName} {Name}";
        }

        public override bool Equals(object obj)
        {
            return obj is Person person &&
                   Name == person.Name &&
                   FirstName == person.FirstName;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Name, FirstName);
        }
    }
}
