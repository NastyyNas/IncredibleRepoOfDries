using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToDoListApp.AppLogic;
using ToDoListApp.Domain;

namespace ToDoListApp.Infrastructure
{
    internal class ToDoItemRepository : IToDoItemRepository
    {
        private ToDoListContext Context;

        public ToDoItemRepository(ToDoListContext context)
        {
            Context = context;
        }

        public int GetTotalOfItemsThatAreNotDone()
        {
            return Context.GetToDoItems().Count(item => !item.IsDone);
        }

        public ToDoItem Update(Guid id, bool isDone)
        {
            ToDoItem item = Context.GetToDoItems().FirstOrDefault(item => item.Id == id);
            item.IsDone = isDone;
            return item;
        }
    }
}
