package models;

import javax.persistence.*;

@Entity
@Table(name = "hibernate_reimbursement")
public class Reimbursement {
    @Id
    @Column(name="reimbursement_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reimbursement_ID;
    @Column
    private double reimbursement_amount;
    @Column
    private String reimbursement_description;
    @Column
    private String employee_name;
    @Column
    private String reimbursement_status;

    public Reimbursement() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Reimbursement(int reimbursement_ID, double reimbursement_amount, String reimbursement_description, String employee_name, String reimbursement_status) {
        super();
        this.reimbursement_ID = reimbursement_ID;
        this.reimbursement_amount = reimbursement_amount;
        this.reimbursement_description = reimbursement_description;
        this.employee_name = employee_name;
        this.reimbursement_status = reimbursement_status;
    }

    public int getReimbursement_ID() {
        return reimbursement_ID;
    }

    public void setReimbursement_ID(int reimbursement_ID) {
        this.reimbursement_ID = reimbursement_ID;
    }

    public double getReimbursement_amount() {
        return reimbursement_amount;
    }

    public void setReimbursement_amount(double reimbursement_amount) {
        this.reimbursement_amount = reimbursement_amount;
    }

    public String getReimbursement_description() {
        return reimbursement_description;
    }

    public void setReimbursement_description(String reimbursement_description) {
        this.reimbursement_description = reimbursement_description;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getReimbursement_status() {
        return reimbursement_status;
    }

    public void setReimbursement_status(String reimbursement_status) {
        this.reimbursement_status = reimbursement_status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Double reimbursement_amounts = (Double)reimbursement_amount;
        result = prime * result + ((reimbursement_description == null) ? 0 : reimbursement_description.hashCode());
        result = prime * result + reimbursement_ID;
        result = prime * result + (((Double)reimbursement_amount == null) ? 0 : reimbursement_amounts.hashCode());
        result = prime * result + ((employee_name == null) ? 0 : employee_name.hashCode());
        result = prime * result + ((reimbursement_status == null) ? 0 : reimbursement_status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reimbursement other = (Reimbursement) obj;
        if (reimbursement_description == null) {
            if (other.reimbursement_description != null)
                return false;
        } else if (!reimbursement_description.equals(other.reimbursement_description))
            return false;
        if (reimbursement_ID != other.reimbursement_ID)
            return false;
        if ((Double)reimbursement_amount == null)
            return false;
        if (employee_name == null) {
            if (other.employee_name != null)
                return false;
        } else if (!employee_name.equals(other.employee_name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reimbursement [reimbursement_id=" + reimbursement_ID + ", reimbursement_amount=" + reimbursement_amount + ", reimbursement_description="
                + reimbursement_description + ", employee_name=" + employee_name + ", reimbursement_status=" + reimbursement_status + "]";
    }

}
