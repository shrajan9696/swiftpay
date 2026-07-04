package com.analytics_service.repo;

import com.analytics_service.entity.AnalyticsEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyticsRepository extends JpaRepository<AnalyticsEvent, Long> {
}