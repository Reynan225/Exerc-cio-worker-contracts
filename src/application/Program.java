package application;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities_enum.WorkerLevel;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();
		System.out.println("Enter worker data: ");
		System.out.print("Name: : ");
		String workerName = sc.nextLine();
		System.out.print("Leve: ");
		String workerlevel = sc.nextLine().toUpperCase();
		System.out.print("Base salary: ");
		double baseSalary = sc.nextDouble();

		System.out.print("How many contracts to this worker? ");
		int numberContracts = sc.nextInt();
		sc.nextLine();

		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerlevel), baseSalary,
				new Department(departmentName));

		for (int i = 0; i < numberContracts; i++) {
			System.out.printf("Enter contract #%d data:\n", i + 1);
			System.out.print("Date (DD/MM/YYYY): ");
			String date = sc.nextLine();
			LocalDate d01 = LocalDate.parse(date, fmt1);
			
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();

			HourContract contract = new HourContract(d01, valuePerHour, hours);
			worker.addContract(contract);
			
			sc.nextLine();
		}

		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("MM/yyyy");
		
		System.out.print("\nEnter month and year to calculate income (MM/yyyy): ");
		String monthAndYear = sc.nextLine();
		
		YearMonth ym = YearMonth.parse(monthAndYear, fmt2);
		int year = ym.getYear();
		int month = ym.getMonthValue();
		
		worker.income(year, month);
		
		System.out.println("Nome: " + worker.getName() + "\nDepartment: " +
				worker.getDepartment().getName() + "\nIncome for: " + ym + ": " +
				String.format("%.2f", worker.income(year, month)));
		
		sc.close();
	}

}