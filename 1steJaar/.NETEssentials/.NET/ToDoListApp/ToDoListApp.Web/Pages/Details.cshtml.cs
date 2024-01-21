using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using ToDoListApp.AppLogic;

namespace ToDoListApp.Web.Pages
{
    public class DetailsModel : searchModel
    {
        public DetailsModel(IToDoListRepository toDoListRepository) : base(toDoListRepository)
        {
        }

        public void OnGet()
        {
        }
    }
}
