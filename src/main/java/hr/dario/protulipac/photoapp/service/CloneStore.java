package hr.dario.protulipac.photoapp.service;

import hr.dario.protulipac.photoapp.domain.PictureInt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class CloneStore {
    private  Hashtable<Long, List<PictureInt>> clones = new Hashtable<>();

    public  List<PictureInt> getClones(Long id) {
        return clones.get(id);
    }

    public  void putClone(Long id, PictureInt cloneInt) {
        if (clones.get(id) == null) {
            List<PictureInt> cl = new ArrayList<>();
            cl.add(cloneInt);
            clones.put(id, cl);
        }
        else {
            clones.get(id).add(cloneInt);
        }
    }
}
