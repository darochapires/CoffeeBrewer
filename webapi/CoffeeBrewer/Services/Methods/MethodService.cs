using CoffeeBrewer.Database;
using CoffeeBrewer.Models;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Services.Methods
{
    public class MethodService : IMethodService
    {
        private readonly CoffeeBrewerContext _context;

        public MethodService(CoffeeBrewerContext context) 
        {
            _context = context;
        }

        public ErrorOr<Created> CreateMethod(Method method)
        {
            _context.Add(method);
            if(!Save()) 
            {
                return Errors.Method.UnexpectedError;
            }
            return Result.Created;
        }

        public ErrorOr<Method> GetMethod(int id)
        {
            var method = _context.Methods.Where(m => m.Id == id).FirstOrDefault();
            return method == null ? Errors.Method.NotFound : method;
        }

        public ErrorOr<List<Method>> GetMethods()
        {
            return _context.Methods.ToList();
        }

        public ErrorOr<Updated> UpsertMethod(Method method)
        {
            if(!_context.Methods.Any(m => m.Id == method.Id)) 
            {
                return Errors.Method.NotFound;
            }            

            _context.Update(method);
            if(!Save()) 
            {
                return Errors.Method.UnexpectedError;
            }
            return Result.Updated;
        }

        public ErrorOr<Deleted> DeleteMethod(int id)
        {
            var method = _context.Methods.FirstOrDefault(m => m.Id == id);
            if(method == null)
            {
                return Errors.Method.NotFound;
            }

            _context.Remove(method);
            if(!Save()) 
            {
                return Errors.Method.UnexpectedError;
            }
            return Result.Deleted;
        }

        private bool Save()
        {
            var saved = _context.SaveChanges();
            return saved > 0 ? true : false;
        }
    }
}