package bg.tu_varna.sit.AppCommands;

import bg.tu_varna.sit.MainClasses.Storage;

public class CommandBonusStatistics {
       public void showStatistics(Storage storage) {
        storage.generateViewershipStatistics();
    }

}
