using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToDoListApp.AppLogic;
using ToDoListApp.Domain;

namespace ToDoListApp.Infrastructure
{
    internal class ToDoListRepository : IToDoListRepository
    {
        private ToDoListContext Context;

        public ToDoListRepository(ToDoListContext context)
        {
            Context = context;
        }

        public void AddItemToExistingList(Guid listId, string itemDescription)
        {
            Context.Lists.FirstOrDefault(item => item.Id == listId).Items.Add(ToDoItem.CreateNew(itemDescription));

        }

        public ToDoList AddNew(string title)
        {
            if(title.Count() > 4)
            {
                ToDoList list = ToDoList.CreateNew(title);
                Context.Lists.Add(list);
                return list;
            }
            else
            {
                return null;
            }
            
        }

        public IList<ToDoList> Find(string? titleFilter)
        {
            if(titleFilter == null)
            {
                return Context.Lists.ToList();
            }
            else
            {
                return Context.Lists.Where(list => list.Title == titleFilter).ToList();
            }
        }

        public ToDoList? GetById(Guid id)
        {
            return (ToDoList) Context.Lists.Where(list => list.Id == id);
        }
    }
}
