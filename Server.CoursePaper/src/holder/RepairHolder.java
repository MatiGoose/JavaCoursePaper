package holder;

import Interfaces.Repository;
import Interfaces.Specification;
import Observer.RepairObserver;
import Entities.Repair;

import java.util.ArrayList;
import java.util.List;

public class RepairHolder implements Repository<Repair>
{
    private static RepairHolder instance;
    private ArrayList<Repair> repairList;
    private RepairObserver observer = new RepairObserver();

    public static RepairHolder getInstance() {
        if (instance == null) {
            instance = new RepairHolder();
        }
        return instance;
    }

    private RepairHolder() {
        repairList = new ArrayList<>();
    }

    public List<Repair> getRepairList() {
        return repairList;
    }

    @Override
    public void add(Repair repair) {
        if (repairList.size() < 1) {
            repair.setRepairID(1);
        } else {
            repair.setRepairID(repairList.get(repairList.size() - 1).getRepairID() + 1);
        }
        repairList.add(repair);
        observer.onAdd(repair);

    }

    public void initialize(Repair repair) {
        repairList.add(repair);
    }

    @Override
    public void remove(Repair repair) {
        repairList.remove(repair);
        observer.onDelete(repair);
    }

    @Override
    public void update(Repair newRepair, Repair oldRepair) {
        int repairPos = repairList.indexOf(oldRepair);
        newRepair.setRepairID(oldRepair.getRepairID());
        repairList.set(repairPos, newRepair);
        observer.onUpdate(newRepair, oldRepair);

    }

    @Override
    public List<Repair> query(Specification<Repair> specification) {
        List<Repair> repairs = new ArrayList<>();
        for (Repair repair : repairList) {
            if (specification.specify(repair)) {
                repairs.add(repair);
            }
        }
        return repairs;
    }
}
