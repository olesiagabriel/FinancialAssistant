package com.example.myfinancialassistant.ViewModel;

import android.app.Application;

public class MyApplication extends Application {

    private int someVariable;
    private int ExpenseUpdateId;
    private int IncomeUpdateId;
    private int PaymentId;
    private String  Expense_guide_Id="";
    private String  Income_guide_Id="";
    private String  Start_date="";
    private String  Stop_date="";
    int diagramposition=0;
    int expenseposition=0;
    int incomeposition=0;
    int tabidstatistics=0;
    int ReportIncomeSize=0;
    public int getExpenseUpdateId() {
        return ExpenseUpdateId;
    }

    public void setExpenseUpdateId(int ExpenseUpdateId) {
        this.ExpenseUpdateId = ExpenseUpdateId;
    }

    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int PaymentId) {
        this.PaymentId = PaymentId;
    }

    public int getReportIncomeSize() {
        return ReportIncomeSize;
    }

    public void setReportIncomeSize(int ReportIncomeSize) { this.ReportIncomeSize = ReportIncomeSize;
    }

    public int gettabidstatistics() {
        return tabidstatistics;
    }

    public void settabidstatistics(int tabidstatistics) {
        this.tabidstatistics = tabidstatistics;
    }

    public String getStart_date() {
        return Start_date;
    }

    public String getStop_date() {
        return Stop_date;
    }

    public void setStart_date(String Start_date) { this.Start_date = Start_date; }

    public void setStop_date(String Stop_date) { this.Stop_date = Stop_date; }

    public String getExpense_guide_Id() {
        return Expense_guide_Id;
    }

    public void setExpense_guide_Id(String Expense_guide_Id) { this.Expense_guide_Id = Expense_guide_Id; }

    public String getIncome_guide_Id() {
        return Income_guide_Id;
    }

    public void setIncome_guide_Id(String Income_guide_Id) { this.Income_guide_Id = Income_guide_Id; }

    public int getIncomeUpdateId() {
        return IncomeUpdateId;
    }

    public void setIncomeUpdateId(int IncomeUpdateId) {
        this.IncomeUpdateId = IncomeUpdateId;
    }

    public int getdiagramposition() {
        return diagramposition;
    }

    public void setdiagramposition(int diagramposition) {
        this.diagramposition = diagramposition;
    }

    public int getexpenseposition() {
        return expenseposition;
    }

    public void setexpenseposition(int expenseposition) {
        this.expenseposition = expenseposition;
    }

    public int getincomeposition() {
        return incomeposition;
    }

    public void setincomeposition(int incomeposition) {
        this.incomeposition = incomeposition;
    }

    public int getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(int someVariable) {
        this.someVariable = someVariable;
    }
}
