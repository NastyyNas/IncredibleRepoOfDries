using System.ComponentModel.DataAnnotations;

namespace ToDoListApp.Web.Models;

public class AddNewToDoListModel
{
    public string Title { get; set; } = null!;
}