package fileCleaner;

import java.util.List;

public interface DAO<T> {

	List<T> getAll();
	
}
