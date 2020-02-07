package sample;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private ListView<sample.Employee> employeeListView;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveheckBox;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //built in selection model changes background color for selection and changes font color when item selected. also
        //keeps track of selected item. if nothing is selected points to null.
        //if something selected, points to what is selected (faculty, staff, whatev). Adding listener to it.
        //object now listens for (observable value) any calss that extends worker interface, shows old val (before selection)
        //and new value (after selection).
        employeeListView.getSelectionModel().selectedItemProperty().addListener(
                ObservableValue<? extends Worker> ov, Worker old_val, Worker new_val) ->
                (
                      Worker selectedItem =employeeListView.getSelectionModel().getSelectedItem();
                      firstNameTextField.setText((Employee)selectedItem).firstName; //casting Employee. can't always assume this will work.
        //depends on how objects are created.
                ));

        ObservableList<Worker> items = employeeListView.getItems();
        Employee employee1 = new Employee();
        employee1.firstName = "Robert";
        employee1.lastName = "Smith";
        Employee employee 2 = new Employee();
        employee2.firstName="Lisa";
        employee2.lastName = "Smith";
        items.add(employee1);
        items.add(employee2);

        for(int i = 0; i < 10; i++)
        {
            Employee employee = new sample.Employee();
            employee.firstName = "Generic";
            employee.lastName = "Employee" + " " + i;
            employee.hire(); 
            items.add(employee);
        }

        Staff staff1 = new Staff();
        staff1.firstName = "Staffperson";
        staff1.lastName = "GoodWorker";

        Faculty faculty1 = new Faculty ();
        faculty1.firstName = "FacultyPerson";
        faculty1.lastName = "TerribleWorker";

        items.add(staff1);
        items.add(faculty1);
    }
}