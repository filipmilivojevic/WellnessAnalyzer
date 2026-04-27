import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class wellness_analyzer {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Person> people = new ArrayList<>();
        //TODO - fill in the following, to read data from a file, use the data to construct
        // specific Person objects (Student, Teacher, and Staff), and store in a List of Person (polymorphism)
        Scanner file = new Scanner(new File("wellness_data.csv")); //construct Scanner object using appropriate filename (such as wellness_data.csv)
        file.nextLine(); // skip header line.

        while (file.hasNextLine()) { //add appropriate while condition here.
            String line = file.nextLine();// code to read the next line from the .csv file
            String[] parts = line.split(",");// code to split the line into an array of String, using commas as delimiters (separators)

            String role = parts[0]; //store the first item in a variable
            String name = parts[1]; //store the second item in a variable
            int steps = Integer.parseInt(parts[2]); // store the third item in a variable
            double sleep = Double.parseDouble(parts[3]);//store the fourth item in a variable - need to do math on this one, so parse as a double
            double water = Double.parseDouble(parts[4]);//store the fifth  item in a variable - need to do math on this one, so parse as a double

            if (role.equals("student")) {
                Student stu = new Student(name,steps, sleep,water);
                people.add(stu);
                //construct Student object, and add to list of Persons.  This is polymorphism.
            } else if (role.equals("teacher")) {
                Teacher t = new Teacher(name,steps,sleep,water);
                people.add(t);
                //construct Teacher object, and add to list of Persons.  This is polymorphism.
            } else if (role.equals("staff")) {
                Staff sta = new Staff(name,steps,sleep,water);
                people.add(sta);
                //construct Saff object, and add to list of Persons.  This is polymorphism.
            }}
        for (Person p: people){
            System.out.println(p.getName() + " (" + p.getRole() + "): " + p.calculateWellnessScore());
        }
        double mean = 0;
        if (people != null){
            double total = 0;
            for(int i = 0; i<people.size(); i++){
                total += people.get(i).calculateWellnessScore();

            }
            mean = total / people.size();
            System.out.println("Mean:" + mean);
        }
        double studentTotal = 0;
        double teacherTotal = 0;
        double staffTotal = 0;
        int studentcount = 0;
        int teachercount= 0;
        int staffcount = 0;




        for (int i =0; i < people.size(); i ++)
        {
            if (people.get(i).getRole().equals("Student")){
                studentTotal += people.get(i).calculateWellnessScore();
                studentcount++;
            } else if (people.get(i).getRole().equals("Teacher")) {
                teacherTotal += people.get(i).calculateWellnessScore();
                teachercount++;
            } else if (people.get(i).getRole().equals("Staff")) {
                staffTotal += people.get(i).calculateWellnessScore();
                staffcount++;
            }
        }
        System.out.println("Student avg: " + (studentTotal / studentcount));
        System.out.println("Teacher avg: " + (teacherTotal / teachercount));
        System.out.println("Staff avg: "   + (staffTotal   / staffcount));


        // MEdian method
        ArrayList<Double> scores = new ArrayList<>();
        for (int i = 0; i < people.size(); i++){
            scores.add(people.get(i).calculateWellnessScore());
        }
        Collections.sort(scores);
        double min = scores.get(0);
        double max = scores.get(scores.size()-1);
        double median = 0;
        int n = scores.size();
        // math equations for median
        if (n%2==0){
            median = (scores.get((n/2) -1) + scores.get(n/2)) / 2.0;
        }
        else {
            median = scores.get(n / 2);
        }
        System.out.println("Median: " + median);


        // Standard Deviation
        double sumSquaredDiffs = 0;
        for (double s : scores) {
        sumSquaredDiffs += Math.pow(s-mean,2);
        }
        double stdDev = Math.sqrt((sumSquaredDiffs/scores.size()));

        System.out.println("Standard deviation: " + stdDev);
        System.out.println("Highest score: " + max);
        System.out.println("Lowest score: " + min);



    }}


// TODO:
// - compute statistics
// - print results


