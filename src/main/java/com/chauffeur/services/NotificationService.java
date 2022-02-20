package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;


import com.chauffeur.dto.NotificationDto;

public interface NotificationService {
	
	NotificationDto save(NotificationDto notificationDto);
	
	NotificationDto saveNoteToChauffeur(Long idChauff, NotificationDto notificationDto);
	
	NotificationDto update(Long idNotification, NotificationDto notificationDto);

	NotificationDto findById(Long id);

    List<NotificationDto> findAll();
    
    List<NotificationDto> findTop3RatingOrderByCreatedDateDesc();

    List<NotificationDto> findByOrderByIdDesc();

    List<NotificationDto> findTop4ByOrderByCreatedDateDescByChauffeurId(String chauffRef);

    BigDecimal countNumberOfNotificationByChauffeurId(String chauffRef);
    
    BigDecimal countNumberOfNotification();
    
    void delete(Long id);

 

}
