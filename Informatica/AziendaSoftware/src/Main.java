import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Company company = new Company();

    public static void main(String[] args) {
        int input;
        do {
            System.out.println("""
                    0: Add employee
                    1: Add project
                    2: View project
                    3: Exit
                    """);
            input = scanner.nextInt();

            switch (input) {
                case 0 -> {
                    System.out.println("Enter the employee's name");
                    String tempName = scanner.next();
                    System.out.println("Enter the employee's surname");
                    String tempSurname = scanner.next();
                    company.addEmployee(tempName, tempSurname);
                }
                case 1 -> {
                    int temp = company.addProject();
                    if (temp == -1) {
                        System.out.println("There is already a project");
                    } else {
                        company.project.printProjectInfo();
                    }
                }
                case 2 -> {
                    if (company.project == null) {
                        System.out.println("There is no project");
                        break;
                    }
                    projectMenu(company.project);
                }
            }
        } while (input != 3);
    }

    /**
     * The project menu
     *
     * @param project the current project selected, program can be modified for multiple projects
     */
    static void projectMenu(Project project) {
        int input;
        do {
            System.out.println("""
                    0: View Project info
                    1: Add project milestone
                    2: Add project task
                    3: Complete task
                    4: Advance day
                    5: Exit
                    """);
            input = scanner.nextInt();
            switch (input) {
                case 0 -> {
                    project.checkCriticalTasks();
                    project.printProjectInfo();
                }
                case 1 -> {
                    System.out.println("Enter the milestone's name");
                    String temp = scanner.next();
                    project.addMilestone(temp);
                }
                case 2 -> {
                    if (project.milestones.size() > 0) {
                        Milestone tempMilestone = project.getMilestone();

                        System.out.println("Enter the task's assigned employee by index");
                        for (int i = 0; i < project.employees.size(); i++) {
                            if (!project.employees.get(i).isAssigned) {
                                System.out.println(i + ": " + project.employees.get(i).name + " " + project.employees.get(i).surname);
                            }
                        }

                        Employee tempEmployee = project.employees.get(scanner.nextInt());

                        if (project.checkEmployee(tempEmployee) == -1) {
                            System.out.println("Employee assigned to another task");
                            break;
                        }

                        System.out.println("Enter the task's name");
                        String tempName = scanner.next();

                        System.out.println("Enter the task's deadline in days");
                        int tempDeadLine = scanner.nextInt();

                        tempMilestone.addTask(tempName, tempDeadLine, tempEmployee);
                    } else {
                        System.out.println("No milestones");
                    }
                }
                case 3 -> {
                    if (project.milestones.size() > 0) {
                        Milestone tempMilestone = project.getMilestone();
                        int temp;

                        System.out.println("Enter the task by index");
                        for (int i = 0; i < tempMilestone.tasks.size(); i++) {
                            if (!tempMilestone.tasks.get(i).isComplete) {
                                System.out.println(i + ": " + tempMilestone.tasks.get(i).name);
                            }
                        }
                        temp = scanner.nextInt();

                        Task tempTask = tempMilestone.tasks.get(temp);
                        System.out.println("Chosen " + tempTask.name);
                        tempTask.isComplete = true;
                        tempTask.assignedEmployee.isAssigned = false;
                        project.checkComplete();
                    } else {
                        System.out.println("No milestones");
                    }
                }
                case 4 -> {
                    project.projectDay++;
                    System.out.println("The day is " + project.projectDay);
                    project.checkComplete();
                    project.checkCriticalTasks();
                    System.out.println("Critical tasks");
                    for (Task task : project.getCriticalTasks()) {
                        System.out.println(task.name + " Due to:" + task.deadline + "Assigned to " + task.name);
                    }
                }
            }
        } while (input != 5);
    }
}

/**
 * Class representing a project
 */
class Project {

    /**
     * Name of the project
     */
    String name;

    /**
     * Milestones of the project
     */
    ArrayList<Milestone> milestones = new ArrayList<>();

    /**
     * Employees assigned to the project
     */
    ArrayList<Employee> employees;

    /**
     * Indicates whether the project is complete
     */
    boolean isComplete = false;

    /**
     * Indicates the overall percentage of the project by dividing the completed milestones
     */
    float percentage = 0.0f;

    /**
     * The projects' day
     */
    int projectDay = 0;

    public Project(String name, ArrayList<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    /**
     * Helper method for adding milestones
     *
     * @param name name of the milestones
     */
    void addMilestone(String name) {
        milestones.add(new Milestone(name));
    }

    /**
     * Prints the project info including each milestone and each task
     */
    void printProjectInfo() {
        calcPerc();
        System.out.println("Project info:\n" + "Name : " + name + "\n" + "Percentage: " + percentage + "\n" + "Employees assigned : " + employees.size() + "\n");
        printEmployees();

        System.out.println("Milestones");
        for (Milestone milestone : milestones) {
            milestone.checkComplete();
            System.out.println("Milestone: " + milestone.name + ", Percentage: " + milestone.percentage + ", " + (milestone.isComplete ? "Completed" : "Not completed"));
            for (Task task : milestone.tasks) {
                System.out.println("Task " + task.name + ", Deadline:" + task.deadline + ", Employee assigned " + task.assignedEmployee.name + " " + task.assignedEmployee.surname + ", " + (task.isComplete ? "Completed" : "Not completed") + ", " + (task.isCritical ? "Critical" : "Not critical"));
            }
        }
        System.out.println();
    }

    /**
     * Helper method for printProjectInfo() to print all employees
     */
    void printEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee.name + " " + employee.surname);
        }
    }

    /**
     * Checks if a employee is already assigned to a non-complete task
     *
     * @return 0 if an employee is not assigned to a non-complete task <br>
     * -1 if an employee is already assigned to a non-complete task
     */
    int checkEmployee(Employee employee) {
        for (Milestone milestone : milestones) {
            for (Task task : milestone.tasks) {
                if (task.assignedEmployee == employee && !task.isComplete) return -1;
            }
        }
        return 0;
    }

    /**
     * Gets all the critical tasks
     *
     * @return A list containing all critical tasks
     */
    ArrayList<Task> getCriticalTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Milestone milestone : milestones) {
            for (Task task : milestone.tasks) {
                if (task.isCritical) {
                    taskArrayList.add(task);
                }
            }
        }
        return taskArrayList;
    }

    /**
     * Gets a milestone through user input
     *
     * @return The selected milestone
     */
    Milestone getMilestone() {
        System.out.println("Enter the milestone by index");
        for (int i = 0; i < milestones.size(); i++) {
            if (!milestones.get(i).isComplete) {
                System.out.println(i + ": " + milestones.get(i).name);
            }
        }
        int temp = Main.scanner.nextInt();
        System.out.println("Chosen " + milestones.get(temp).name);
        return milestones.get(temp);
    }

    /**
     * Calculates the project percentage
     */
    void calcPerc() {
        percentage = (getCompletedMilestones() / milestones.size()) * 100;
    }

    /**
     * Gets all completed milestones
     *
     * @return Number of completed milestones
     */
    float getCompletedMilestones() {
        float counter = 0.0f;
        for (Milestone milestone : milestones) {
            milestone.calcPerc();
            if (milestone.isComplete) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Checks for critical tasks and sets them as required
     */
    void checkCriticalTasks() {
        for (Milestone milestone : milestones) {
            for (Task task : milestone.tasks) {
                if (projectDay > task.deadline) {
                    task.isCritical = true;
                }
            }
        }
    }

    /**
     * Checks is the project is completed
     */
    void checkComplete() {
        for (Milestone milestone : milestones) {
            for (Task task : milestone.tasks) {
                if (!task.isComplete) {
                    isComplete = false;
                    return;
                }
            }
        }
        isComplete = true;
    }
}

/**
 * Class representing the individual employee
 */
class Employee {
    /**
     * Indicates whether the employee is assigned to a task
     */
    boolean isAssigned = false;

    /**
     * Name of the employee
     */
    String name;

    /**
     * Surname of the employee
     */
    String surname;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}

/**
 * Class representing a milestone
 */
class Milestone {
    /**
     * Name of the milestone
     */
    String name;

    /**
     * Indicates the overall percentage of the project by dividing the completed tasks
     */
    float percentage = 0.0f;

    /**
     * Tasks of this milestone
     */
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Indicates whether the milestone is complete
     */
    boolean isComplete = false;

    public Milestone(String name) {
        this.name = name;
    }

    /**
     * Checks whether the milestone is complete by enumerating through all the tasks
     */
    void checkComplete() {
        int notComplete = 0;
        for (Task task : tasks) {
            if (!task.isComplete) {
                notComplete++;
            }
        }
        isComplete = notComplete == 0;
    }

    /**
     * Calculates the milestone percentage
     */
    void calcPerc() {
        percentage = (getCompletedTasks() / tasks.size()) * 100;
    }

    /**
     * Gets all the completed tasks
     *
     * @return The number of completed tasks
     */
    float getCompletedTasks() {
        float counter = 0.0f;
        for (Task task : tasks) {
            if (task.isComplete) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Helper method for adding tasks
     *
     * @param name             Name of the task
     * @param deadline         Deadline in days of the task
     * @param assignedEmployee The employee assigned to the task
     */
    void addTask(String name, int deadline, Employee assignedEmployee) {
        assignedEmployee.isAssigned = true;
        tasks.add(new Task(name, deadline, assignedEmployee));
    }
}

/**
 * Class representing a task
 */
class Task {
    /**
     * Name of the task
     */
    String name;

    /**
     * Deadline of the task in days
     */
    int deadline;

    /**
     * Indicates whether the task is critical, meaning the deadline has been passed
     */
    boolean isCritical = false;

    /**
     * Indicates whether the task is completed
     */
    boolean isComplete = false;

    /**
     * Employee assigned to the task
     */
    Employee assignedEmployee;

    public Task(String name, int deadline, Employee assignedEmployee) {
        this.name = name;
        this.deadline = deadline;
        this.assignedEmployee = assignedEmployee;
    }
}

/**
 * Class representing the company
 */
class Company {
    /**
     * List containing all the employees who work at the company
     */
    ArrayList<Employee> employees = new ArrayList<>();

    /**
     * Current project
     */
    Project project;

    /**
     * Helper method for adding an employee
     *
     * @param name    Name of the employee
     * @param surname Surname of the employee
     */
    void addEmployee(String name, String surname) {
        employees.add(new Employee(name, surname));
    }

    /**
     * Adds or replaces a project
     *
     * @return 0 if a project is successfully added <br>
     * -1 if a project doesn't get added
     */
    int addProject() {
        try {
            if (!this.project.isComplete) {
                return -1;
            }
        } catch (NullPointerException ignored) {
        }
        System.out.println("Enter the project name");
        String tempName = Main.scanner.next();
        ArrayList<Employee> tempEmployeeList = new ArrayList<>();
        System.out.println("Enter employees by index who will work on this project\n" + "Enter -1 to exit");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(i + ": " + employees.get(i).name + " " + employees.get(i).surname);
        }
        int temp;
        do {
            temp = Main.scanner.nextInt();
            if (temp != -1) {
                tempEmployeeList.add(this.employees.get(temp));
            }
        } while (temp != -1);
        project = new Project(tempName, tempEmployeeList);
        return 0;
    }
}