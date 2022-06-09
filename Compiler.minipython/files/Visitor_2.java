import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class Visitor_2 extends DepthFirstAdapter {

    private Hashtable<String, Node> symbolTable;
    private Hashtable symbolType;
    private Hashtable<String, String> exprTypes;

    Visitor_2(Hashtable symbolTable, Hashtable exprTypes) {
        this.symbolTable = symbolTable;
        this.exprTypes = exprTypes;
    }


    // function_call
    public void inAFunctionCallStatement(AFunctionCallStatement node) {
        String func_name = ((AFuncName)((AFunctionCall)node.getFunctionCall()).getFuncName()).getIdentifier().toString();
        int line = ((AFuncName)((AFunctionCall)node.getFunctionCall()).getFuncName()).getIdentifier().getLine();
        int position = ((AFuncName)((AFunctionCall)node.getFunctionCall()).getFuncName()).getIdentifier().getPos();
        if (!symbolTable.containsKey(func_name)) {
            System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + func_name);
        }
    }


    // Assignments
    @Override
    public void inAAssignStatement(AAssignStatement node) {
        String variable = ((AVarName)node.getVarName()).getIdentifier().toString();
        PExpression expression = node.getExpression();
        if (expression instanceof AIdentifierExpression) {
            String var = ((AIdentifierExpression) expression).getIdentifier().toString();
            int line = ((AIdentifierExpression) expression).getIdentifier().getLine();
            int position = ((AIdentifierExpression) expression).getIdentifier().getPos();
            if (!symbolTable.containsKey(var)) {
                System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + var);
            }
        }
        if (symbolTable.containsKey(variable)) {
            symbolTable.replace(variable, node);
        }
        else {
            symbolTable.put(variable, node);
        }
    }
    @Override
    public void inAPlusEqStatement(APlusEqStatement node) {
        String variable = ((AVarName)node.getVarName()).getIdentifier().toString();
        int line = ((AVarName)node.getVarName()).getIdentifier().getLine();
        int position = ((AVarName)node.getVarName()).getIdentifier().getPos();
        if (!symbolTable.containsKey(variable)) {
            System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + variable);
        }
        else {
            symbolTable.replace(variable, node);
        }
    }
    @Override
    public void inAMultEqStatement(AMultEqStatement node) {
        String variable = ((AVarName)node.getVarName()).getIdentifier().toString();
        int line = ((AVarName)node.getVarName()).getIdentifier().getLine();
        int position = ((AVarName)node.getVarName()).getIdentifier().getPos();
        if (!symbolTable.containsKey(variable)) {
            System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + variable);
        }
        else {
            symbolTable.replace(variable, node);
        }
    }
    @Override
    public void inADivEqStatement(ADivEqStatement node) {
        String variable = ((AVarName)node.getVarName()).getIdentifier().toString();
        int line = ((AVarName)node.getVarName()).getIdentifier().getLine();
        int position = ((AVarName)node.getVarName()).getIdentifier().getPos();
        if (!symbolTable.containsKey(variable)) {
            System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + variable);
        }
        else {
            symbolTable.replace(variable, node);
        }
    }


    // statements
    @Override
    public void inAArraySliceStatement(AArraySliceStatement node) {
        String variable = ((AVarName)node.getVarName()).getIdentifier().toString();
        int line = ((AVarName)node.getVarName()).getIdentifier().getLine();
        int position = ((AVarName)node.getVarName()).getIdentifier().getPos();
        if (!symbolTable.containsKey(variable)) {
            System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + variable);
        }
    }
    @Override
    public void inAForStatement(AForStatement node) {
        String identifier = ((AIdentifierValue)node.getValue()).getIdentifier().toString();
        symbolTable.put(identifier, node);

        String list = ((AList)node.getList()).getIdentifier().toString();
        int line = ((AList)node.getList()).getIdentifier().getLine();
        int position = ((AList)node.getList()).getIdentifier().getPos();
        if (!symbolTable.containsKey(list)) {
            System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + list);
        }
    }
    @Override
    public void inAPrintStatement(APrintStatement node) {
        PExpression expression = node.getExpr1();
        LinkedList expression2 = node.getExpr2();

        String expr1 = null;
        int line = 0;
        int position = 0;

        if (expression instanceof AIdentifierExpression) {
            expr1 = ((AIdentifierExpression)expression).getIdentifier().toString();
            line = ((AIdentifierExpression)expression).getIdentifier().getLine();
            position = ((AIdentifierExpression)expression).getIdentifier().getPos();
            if (!symbolTable.containsKey(expr1)) {
                System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + expr1);
            }
        }
        for (int i = 0; i < expression2.size(); i++) {
            if (expression2.get(i) instanceof AIdentifierExpression) {
                expr1 = ((AIdentifierExpression) expression2.get(i)).getIdentifier().toString();
                line = ((AIdentifierExpression) expression2.get(i)).getIdentifier().getLine();
                position = ((AIdentifierExpression) expression2.get(i)).getIdentifier().getPos();
                if (!symbolTable.containsKey(expr1)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + expr1);
                }
            }
        }
    }
    @Override
    public void inAReturnStatement(AReturnStatement node) {
        PExpression expression = node.getExpression();
        String identifier = null;
        int line = 0;
        int position = 0;
        if (expression instanceof AIdentifierExpression) {
            identifier = ((AIdentifierExpression)expression).getIdentifier().toString();
            line = ((AIdentifierExpression)expression).getIdentifier().getLine();
            position = ((AIdentifierExpression)expression).getIdentifier().getPos();
            if (!symbolTable.containsKey(identifier)) {
                System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
            }
        }
    }
    @Override
    public void inAAssignArrayStatement(AAssignArrayStatement node) {
        String identifier = ((AVarName)node.getVarName()).getIdentifier().toString();
        int line = ((AVarName)node.getVarName()).getIdentifier().getLine();
        int position = ((AVarName)node.getVarName()).getIdentifier().getPos();
        if (!symbolTable.containsKey(identifier)) {
            System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
        }
    }

    // expressions
    @Override
    public void inASubscriptionExpression(ASubscriptionExpression node) {
        PExpression expression = node.getExpression();
        String identifier = ((AVarName)node.getVarName()).getIdentifier().toString();
        int line = ((AVarName)node.getVarName()).getIdentifier().getLine();
        int position = ((AVarName)node.getVarName()).getIdentifier().getPos();
        if (!symbolTable.containsKey(identifier)) {
            System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
        }
        if (expression instanceof AIdentifierExpression) {
            identifier = ((AIdentifierExpression) expression).getIdentifier().toString();
            line = ((AIdentifierExpression) expression).getIdentifier().getLine();
            position = ((AIdentifierExpression) expression).getIdentifier().getPos();
            if (!symbolTable.containsKey(identifier)) {
                System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
            }
        }
    }
    @Override
    public void inAPowerExpression(APowerExpression node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        String identifier = null;
        int line = 0;
        int position = 0;
        for(PExpression expr: expressions) {
            if (expr instanceof AIdentifierExpression) {
                identifier = ((AIdentifierExpression) expr).getIdentifier().toString();
                line = ((AIdentifierExpression) expr).getIdentifier().getLine();
                position = ((AIdentifierExpression) expr).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inAMultiplicationExpression(AMultiplicationExpression node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        String identifier = null;
        int line = 0;
        int position = 0;
        for(PExpression expr: expressions) {
            if (expr instanceof AIdentifierExpression) {
                identifier = ((AIdentifierExpression) expr).getIdentifier().toString();
                line = ((AIdentifierExpression) expr).getIdentifier().getLine();
                position = ((AIdentifierExpression) expr).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inADivisionExpression(ADivisionExpression node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        String identifier = null;
        int line = 0;
        int position = 0;
        for(PExpression expr: expressions) {
            if (expr instanceof AIdentifierExpression) {
                identifier = ((AIdentifierExpression) expr).getIdentifier().toString();
                line = ((AIdentifierExpression) expr).getIdentifier().getLine();
                position = ((AIdentifierExpression) expr).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inAFloorDivExpression(AFloorDivExpression node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        String identifier = null;
        int line = 0;
        int position = 0;
        for(PExpression expr: expressions) {
            if (expr instanceof AIdentifierExpression) {
                identifier = ((AIdentifierExpression) expr).getIdentifier().toString();
                line = ((AIdentifierExpression) expr).getIdentifier().getLine();
                position = ((AIdentifierExpression) expr).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inAModulusExpression(AModulusExpression node) {
        PExpression expr1 = node.getExpr1(); // x
        PExpression expr2 = node.getExpr2(); // y
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        // [ x , y ] expressions
        String identifier = null;
        int line = 0;
        int position = 0;
        for(PExpression expr: expressions) {
            if (expr instanceof AIdentifierExpression) {
                identifier = ((AIdentifierExpression) expr).getIdentifier().toString();
                line = ((AIdentifierExpression) expr).getIdentifier().getLine();
                position = ((AIdentifierExpression) expr).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inAAdditionExpression(AAdditionExpression node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        String identifier = null;
        int line = 0;
        int position = 0;
        for(PExpression expr: expressions) {
            if (expr instanceof AIdentifierExpression) {
                identifier = ((AIdentifierExpression) expr).getIdentifier().toString();
                line = ((AIdentifierExpression) expr).getIdentifier().getLine();
                position = ((AIdentifierExpression) expr).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inASubtractionExpression(ASubtractionExpression node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        String identifier = null;
        int line = 0;
        int position = 0;
        for(PExpression expr: expressions) {
            if (expr instanceof AIdentifierExpression) {
                identifier = ((AIdentifierExpression) expr).getIdentifier().toString();
                line = ((AIdentifierExpression) expr).getIdentifier().getLine();
                position = ((AIdentifierExpression) expr).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inAFunctionCallExpression(AFunctionCallExpression node) {
        String func_name = ((AFuncName)((AFunctionCall)node.getFunctionCall()).getFuncName()).getIdentifier().toString();
        int line = ((AFuncName)((AFunctionCall)node.getFunctionCall()).getFuncName()).getIdentifier().getLine();
        int position = ((AFuncName)((AFunctionCall)node.getFunctionCall()).getFuncName()).getIdentifier().getPos();
        if (!symbolTable.containsKey(func_name)) {
            System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + func_name);
        }
    }

    // comparisons
    @Override
    public void inALessComparison(ALessComparison node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        for(PExpression expression: expressions) {
            if (expression instanceof AIdentifierExpression) {
                String identifier = ((AIdentifierExpression)expression).getIdentifier().toString();
                int line = ((AIdentifierExpression)expression).getIdentifier().getLine();
                int position = ((AIdentifierExpression)expression).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inAGreatComparison(AGreatComparison node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        for(PExpression expression: expressions) {
            if (expression instanceof AIdentifierExpression) {
                String identifier = ((AIdentifierExpression)expression).getIdentifier().toString();
                int line = ((AIdentifierExpression)expression).getIdentifier().getLine();
                int position = ((AIdentifierExpression)expression).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inAGreatEqComparison(AGreatEqComparison node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        for(PExpression expression: expressions) {
            if (expression instanceof AIdentifierExpression) {
                String identifier = ((AIdentifierExpression)expression).getIdentifier().toString();
                int line = ((AIdentifierExpression)expression).getIdentifier().getLine();
                int position = ((AIdentifierExpression)expression).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inALessEqComparison(ALessEqComparison node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        for(PExpression expression: expressions) {
            if (expression instanceof AIdentifierExpression) {
                String identifier = ((AIdentifierExpression)expression).getIdentifier().toString();
                int line = ((AIdentifierExpression)expression).getIdentifier().getLine();
                int position = ((AIdentifierExpression)expression).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inADiffComparison(ADiffComparison node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        for(PExpression expression: expressions) {
            if (expression instanceof AIdentifierExpression) {
                String identifier = ((AIdentifierExpression)expression).getIdentifier().toString();
                int line = ((AIdentifierExpression)expression).getIdentifier().getLine();
                int position = ((AIdentifierExpression)expression).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }
    @Override
    public void inAEqualComparison(AEqualComparison node) {
        PExpression expr1 = node.getExpr1();
        PExpression expr2 = node.getExpr2();
        ArrayList<PExpression> expressions = new ArrayList<>();
        expressions.add(expr1);
        expressions.add(expr2);
        for(PExpression expression: expressions) {
            if (expression instanceof AIdentifierExpression) {
                String identifier = ((AIdentifierExpression)expression).getIdentifier().toString();
                int line = ((AIdentifierExpression)expression).getIdentifier().getLine();
                int position = ((AIdentifierExpression)expression).getIdentifier().getPos();
                if (!symbolTable.containsKey(identifier)) {
                    System.out.println("Error: [" + line + "/" + position + "] - " + "Cannot find symbol: " + identifier);
                }
            }
        }
    }



}