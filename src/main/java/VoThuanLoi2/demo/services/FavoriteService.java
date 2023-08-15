package VoThuanLoi2.demo.services;

import VoThuanLoi2.demo.respository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
}
