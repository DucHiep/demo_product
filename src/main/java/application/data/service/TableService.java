package application.data.service;

import application.data.model.Table;
import application.data.repository.TableRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class TableService {
    public static final Logger logger = LogManager.getLogger(OrderService.class) ;

    @Autowired
    private TableRepository tableRepository ;
    @Transactional
    public void addNewListTables (List<Table> tables){
        tableRepository.save(tables);
    }

    public List<Table> getListAllTables() {
        try {
            return tableRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }
    public void addNewTable(Table table) {
        tableRepository.save(table);
    }

    public boolean updateTable(Table table) {
        try {
            tableRepository.save(table);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteTable(int tableId) {
        try {
            tableRepository.delete(tableId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }



    public Table findOne(int tableId) {
        return tableRepository.findOne(tableId);
    }
}
