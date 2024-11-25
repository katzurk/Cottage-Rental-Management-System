package MojaChata.pl.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private CottageRepository userRepository;
    private LoginRepository loginRepository;

    @Autowired
    public DataLoader(CottageRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        LoadCottages();
        LoadUsers();
    }

    private void LoadCottages() {
        userRepository.save(new Cottage("micro cottage", "mini 5", 23, 1, 1, 2, 10));
        userRepository.save(new Cottage("the Grand Villa", "epic 20", 600, 100, 50, 100, 120000));
    }

    private void LoadUsers(){
        loginRepository.save(new Login("jan", "kowalski"));
        
    }
    
}
