using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using ToDoListApp.AppLogic;
using ToDoListApp.Domain;

namespace ToDoListApp.Web.Controllers.api
{
    [ApiController]
    [Route("api/[controller]")]
    public class ToDoListController : ControllerBase
    {
        private IToDoListRepository toDoListRepository;
        private IToDoItemRepository toDoItemRepository;
        public ToDoListController(IToDoListRepository toDoListRepository, IToDoItemRepository toDoItemRepository)
        {
            this.toDoListRepository = toDoListRepository;
            this.toDoItemRepository = toDoItemRepository;
        }

        [HttpGet("{id:guid}")]
        public IActionResult Get(Guid id)
        {
            if(toDoListRepository.GetById(id) == null)
            {
                return NotFound();
            }
            else
            {
                return Ok(toDoListRepository.GetById(id));
            }
        }

        [HttpPost]
        public IActionResult AddNewToDoListModel([FromBody] ToDoList toDoList)
        {
            toDoListRepository.AddNew(toDoList.Title);
            return Created(nameof(Get), toDoList );
        }

        [HttpPut("{listId}/items/{ItemId}")]
        public IActionResult UpdateToDoItemModel(Guid listId, Guid itemId, [FromBody] ToDoItem toDoItem)
        {
            ToDoList list = toDoListRepository.GetById(listId);
            if(list == null)
            {
                return NotFound();
            }

            ToDoItem item = list.Items.FirstOrDefault(item => item.Id == itemId);
            if(item == null)
            {
                return NotFound();
            }

            if (item.IsDone)
            {
                return Ok(toDoItemRepository.Update(item.Id, false));
            }
            else
            {
                return Ok(toDoItemRepository.Update(item.Id, true));
            }

            

        }


    }
}
