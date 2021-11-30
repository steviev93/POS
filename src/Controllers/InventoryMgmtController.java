package Controllers;

import Models.Credentials;

public class InventoryMgmtController implements IController {
    private Credentials credentials;

    @Override
    public void loadCredentials(Credentials c) {

        credentials = c;
    }
}
