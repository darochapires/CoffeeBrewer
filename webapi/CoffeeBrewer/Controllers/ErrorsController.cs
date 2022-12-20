using Microsoft.AspNetCore.Mvc;

namespace CoffeeBrewer.Controllers;

public class ErrorsController : ControllerBase
{
    [Route("/error")]
    internal IActionResult Error()
    {
        return Problem();
    }
}