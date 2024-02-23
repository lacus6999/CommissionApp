package src.commission.dto;

import src.commission.CommissionType;

public class CommissionRawData {
    private CommissionType commissionType;
    private String businessAssociate;
    private double amount;

    public CommissionRawData() {
    }

    public CommissionRawData(CommissionType commissionType, String businessAssociate, double amount) {
        this.commissionType = commissionType;
        this.businessAssociate = businessAssociate;
        this.amount = amount;
    }

    public CommissionType getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(CommissionType commissionType) {
        this.commissionType = commissionType;
    }

    public String getBusinessAssociate() {
        return businessAssociate;
    }

    public void setBusinessAssociate(String businessAssociate) {
        this.businessAssociate = businessAssociate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
