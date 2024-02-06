package th.ac.ku.kps.eng.cpe.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import th.ac.ku.kps.eng.cpe.entity.Customer;
import th.ac.ku.kps.eng.cpe.entity.PhoneNumber;

public class CustomerDAOOld {
	public ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> listOfCustomers = new ArrayList<>();
		try {
			File file = new File("Customer.dat");
			if (!file.exists()) {
				Customer c1 = new Customer("Mr.Smith");
				Customer c2 = new Customer("Mrs.Smith");
				listOfCustomers.add(c1);
				c1.getPhoneNumbers().add(new PhoneNumber("145-1654-1495"));
				listOfCustomers.add(c2);
				saveCustomerList(listOfCustomers);
			} else {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				listOfCustomers = (ArrayList<Customer>) ois.readObject();
				ois.close();
			}
		} catch (Exception e) {
			

			e.printStackTrace();
		}
//		System.out.println("Customer.dat is created at: " + new File("Customer.dat").getAbsolutePath());
//		System.out.println("Users.dat is created at: " + new File("Users.dat").getAbsolutePath());

		return listOfCustomers;
		
	}

	private void saveCustomerList(List<Customer> cusList) {
		try {
			File file = new File("Customer.dat");
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(cusList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Customer getCustomerByName(String name) {
		List<Customer> cusList = getAllCustomers();
		for (Customer cus : cusList) {
			if (cus.getName().equalsIgnoreCase(name)) {
				return cus;
			}
		}
		return null;
	}

	public int addCustomer(Customer cus) {
		List<Customer> cusList = getAllCustomers();
		boolean cusExists = false;
		for (Customer c : cusList) {
			if (c.getName().equalsIgnoreCase(cus.getName())) {
				cusExists = true;
				break;
			}
		}
		if (!cusExists) {
			cusList.add(cus);
			saveCustomerList(cusList);
			return 1;
		}
		return 0;
	}

}
