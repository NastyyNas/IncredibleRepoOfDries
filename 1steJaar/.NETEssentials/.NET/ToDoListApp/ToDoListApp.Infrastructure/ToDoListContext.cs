using Microsoft.EntityFrameworkCore;
using ToDoListApp.Domain;

namespace ToDoListApp.Infrastructure
{
    public class ToDoListContext : DbContext
    {
        public DbSet<ToDoList> Lists { get; set; }

        public ToDoListContext(DbContextOptions<ToDoListContext> options): base(options)
        {

        }

        public IQueryable<ToDoItem> GetToDoItems()
        {
            return Set<ToDoItem>();
        }
    }
}
