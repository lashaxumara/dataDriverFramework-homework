import org.testng.Assert;
import org.testng.annotations.Test;

import static data.Data.name;
import static data.Data.newName;
import static dbSteps.DataBase.*;

public class Querying{

    @Test
    public void QueryTest(){
        int lastId = getLastId() + 1;
        addNewRow(lastId, name, false);
        Assert.assertEquals(rowExist(lastId), 0);
        addNewRow(lastId, name,true);
        Assert.assertEquals(rowExist(lastId), 1);
        updateFirstName(lastId, newName);
        Assert.assertEquals(getFirstName(lastId), newName);
    }

}

