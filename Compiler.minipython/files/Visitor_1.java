import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

// Function Definition
public class Visitor_1 extends DepthFirstAdapter {

    private Hashtable<String, Node> symbolTable;
    private Hashtable<String, ArrayList<Integer>> functionArgs;

    Visitor_1(Hashtable symbolTable, Hashtable functionArgs) {
        this.symbolTable = symbolTable;
        this.functionArgs = functionArgs;
    }

    @Override
    public void inAFunction(AFunction node) {
        String functionName = ((AFuncName) node.getFuncName()).getIdentifier().toString();
        int line = ((AFuncName) node.getFuncName()).getIdentifier().getLine();
        int position = ((AFuncName) node.getFuncName()).getIdentifier().getPos();
        ArrayList<Integer> argumentsCount = howManyArguments(node);
        if (symbolTable.containsKey(functionName)) {
            if (functionArgs.get(functionName).get(0) == argumentsCount.get(0) ||
                    functionArgs.get(functionName).get(1) == argumentsCount.get(1)) {
                System.out.println("Error: [" + line + "/" + position + "] - " + functionName + " is already defined.");
            }
        }
        else {
                symbolTable.put(functionName, node);
                functionArgs.put(functionName, howManyArguments(node));
        }

    }

    public ArrayList<Integer> howManyArguments(AFunction node) {
        int counter = 0;
        ArrayList<Integer> result = new ArrayList<>();
        String funcName = ((AFuncName)node.getFuncName()).getIdentifier().toString();
        AArgument args = ((AArgument)node.getArgument());
        LinkedList<AArgumentItemValue> arg1 = args.getV1();
        LinkedList<AArgumentItemValue> argRest = args.getV2();
        if (arg1.get(0).getV2() == null) {
            counter++;
        }
        for(AArgumentItemValue arg: argRest) {
            if (arg.getV2() == null) {
                counter++;
            }
        }
        result.add(counter);
        result.add(arg1.size() + argRest.size());
        return result;
    }

}
