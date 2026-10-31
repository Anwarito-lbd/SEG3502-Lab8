package seg3x02.employeeGql.resolvers

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput
import java.util.*

@Controller
class EmployeesResolver(private val employeesRepository: EmployeesRepository) {

    @QueryMapping
    fun employees(): List<Employee> = employeesRepository.findAll()

    @QueryMapping
    fun employeeById(@Argument id: String): Employee? =
        employeesRepository.findById(id).orElse(null)

    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: CreateEmployeeInput): Employee {
        if (input.name == null || input.dateOfBirth == null || input.city == null || input.salary == null) {
            throw IllegalArgumentException("name, dateOfBirth, city and salary are required")
        }
        val employee = Employee(input.name, input.dateOfBirth, input.city, input.salary,
            input.gender, input.email)
        employee.id = UUID.randomUUID().toString()
        return employeesRepository.save(employee)
    }

    @MutationMapping
    fun deleteEmployee(@Argument id: String): Boolean {
        if (!employeesRepository.existsById(id)) return false
        employeesRepository.deleteById(id)
        return true
    }
}
