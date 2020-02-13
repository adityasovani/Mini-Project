import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.dao.AssetAllocationDAO;
import com.cg.dao.AssetAllocationDAOImpl;

public class AssetAllocationTest {

	@Test
	public void testAssetAllocation() {
		AssetAllocationDAO assetAllocationDAO = new AssetAllocationDAOImpl();
		
		assetAllocationDAO.changeStatus(101, "rejected", "Lai nataka hotayt");
		
		System.out.println();
	}

}
