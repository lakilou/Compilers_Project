import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

// Function Definition
public class Visitor_3 extends DepthFirstAdapter {

    private Hashtable<String, Node> symbolTable;
    private Hashtable<String, String> functionReturn;

    Visitor_3(Hashtable symbolTable, Hashtable functionReturn) {
        this.symbolTable = symbolTable;
        this.functionReturn = functionReturn;
    }

    @Override
    public void inAFunction(AFunction node) {
        String funcName = ((AFuncName)node.getFuncName()).getIdentifier().toString();
        PStatement statement = node.getStatement();
        if (statement instanceof AReturnStatement) {
            PExpression expression = ((AReturnStatement) statement).getExpression();

        }
    }

    @Override
    public void inAMaxExpression(AMaxExpression node) {
        PValue value1 = node.getV1();
        LinkedList<PValue> valueRest = node.getV2();
        String type = value1.getClass().toString();
        for(PValue value: valueRest) {
            if (!value.getClass().toString().equals(type)) {
                System.out.println("Error: function 'max' must have arguments of the same type.");
            }
        }
    }

    @Override
    public void inAMinExpression(AMinExpression node) {
        boolean flag = false;
        PValue value1 = node.getV1();
        LinkedList<PValue> valueRest = node.getV2();
        String type = value1.getClass().toString();
        for(PValue value: valueRest) {
            if (!value.getClass().toString().equals(type)) {
                System.out.println("Error: function 'max' must have arguments of the same type.");
            }
        }
    }

    @Override
    public void inAAdditionExpression(AAdditionExpression node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals(expr2_type)) {
            System.out.println("Error: operator '+' must have the same type of operands.");
        }
    }
    @Override
    public void inASubtractionExpression(ASubtractionExpression node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals("class minipython.node.ANumberValue")) {
            System.out.println("Error: the operator '-' must have numeric operators.");
        }
        if (!expr2_type.equals("class minipython.node.ANumberValue")) {
            System.out.println("Error: the operator '-' must have numeric operators.");
        }
    }
    @Override
    public void inAModulusExpression(AModulusExpression node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals("class minipython.node.ANumberValue")) {
            System.out.println("Error: the operator '%' must have numeric operators.");
        }
        if (!expr2_type.equals("class minipython.node.ANumberValue")) {
            System.out.println("Error: the operator '%' must have numeric operators.");
        }
    }
    @Override
    public void inAFloorDivExpression(AFloorDivExpression node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals("class minipython.node.ANumberValue")) {
            System.out.println("Error: the operator '//' must have numeric operators.");
        }
        if (!expr2_type.equals("class minipython.node.ANumberValue")) {
            System.out.println("Error: the operator '//' must have numeric operators.");
        }
    }
    @Override
    public void inADivisionExpression(ADivisionExpression node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals("class minipython.node.ANumberValue")) {
            System.out.println("Error: the operator '/' must have numeric operators.");
        }
        if (!expr2_type.equals("class minipython.node.ANumberValue")) {
            System.out.println("Error: the operator '/' must have numeric operators.");
        }
    }

    @Override
    public void inALessComparison(ALessComparison node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals(expr2_type)) {
            System.out.println("Error: operator '<' must have the same type of operands.");
        }
    }
    @Override
    public void inAGreatComparison(AGreatComparison node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals(expr2_type)) {
            System.out.println("Error: operator '>' must have the same type of operands.");
        }
    }
    @Override
    public void inAGreatEqComparison(AGreatEqComparison node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals(expr2_type)) {
            System.out.println("Error: operator '>=' must have the same type of operands.");
        }
    }
    @Override
    public void inALessEqComparison(ALessEqComparison node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals(expr2_type)) {
            System.out.println("Error: operator '<=' must have the same type of operands.");
        }
    }
    @Override
    public void inADiffComparison(ADiffComparison node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals(expr2_type)) {
            System.out.println("Error: operator '!=' must have the same type of operands.");
        }
    }
    @Override
    public void inAEqualComparison(AEqualComparison node) {
        AValueExpression expr1 = ((AValueExpression)node.getExpr1());
        AValueExpression expr2 = ((AValueExpression)node.getExpr2());
        String expr1_type = expr1.getValue().getClass().toString();
        String expr2_type = expr2.getValue().getClass().toString();

        if (!expr1_type.equals(expr2_type)) {
            System.out.println("Error: operator '+' must have the same type of operands.");
        }
    }
}