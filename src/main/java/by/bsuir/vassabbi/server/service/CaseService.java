package by.bsuir.vassabbi.server.service;

import by.bsuir.vassabbi.server.dao.DAOFactory;
import by.bsuir.vassabbi.server.model.Case;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

public class CaseService {
    private static final CaseService INSTANCE = new CaseService();

    private CaseService() {
    }

    public static CaseService getInstance() {
        return INSTANCE;
    }

    public Case createCase(NodeList nodes) {
        int id = 0;
        String first = "";
        String last = "";

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                String text = nodes.item(i).getTextContent();
                switch (nodes.item(i).getNodeName()) {
                    case "id" -> id = Integer.parseInt(text);
                    case "firstName" -> first = text;
                    case "lastName" -> last = text;
                    default -> throw new IllegalArgumentException("No such case exists");
                }
            }
        }

        return new Case(id, first, last);
    }

    public Element createNode(Document doc, Case _case) {
        Element e = doc.createElement("case");
        Element id = doc.createElement("id");
        Element first = doc.createElement("firstName");
        Element last = doc.createElement("lastName");
        id.appendChild(doc.createTextNode(String.valueOf(_case.getId())));
        first.appendChild(doc.createTextNode(_case.getFirstName()));
        last.appendChild(doc.createTextNode(_case.getLastName()));
        e.appendChild(id);
        e.appendChild(first);
        e.appendChild(last);
        return e;
    }

    public List<Case> getAll() {
        return DAOFactory.getInstance().getCaseDao().getAll();
    }

    public boolean containsCase(int id) {
        return DAOFactory.getInstance().getCaseDao().contains(id);
    }

    public void editCase(int id, String firstName, String lastName) {
        DAOFactory.getInstance().getCaseDao().setById(id, new Case(0, firstName, lastName));
    }

    public void addCase(String firstName, String lastName) {
        DAOFactory.getInstance().getCaseDao().add(new Case(0, firstName, lastName));
    }
}
