using Microsoft.AspNetCore.Mvc;

namespace CoffeeBrewer.Controllers;

public class ErrorsController : ControllerBase
{
    [Route("/error")]
    public IActionResult Error()
    {
        return Problem();
    }
}