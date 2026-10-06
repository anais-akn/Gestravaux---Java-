package com.immosync.immosyncapp.dto;

public record PrestaStatsDTO(String categorieLibelle, Double totalRevenus) {
    public PrestaStatsDTO(String categorieLibelle, Number totalRevenus) {
        this(categorieLibelle, totalRevenus != null ? totalRevenus.doubleValue() : 0.0);
    }
}
