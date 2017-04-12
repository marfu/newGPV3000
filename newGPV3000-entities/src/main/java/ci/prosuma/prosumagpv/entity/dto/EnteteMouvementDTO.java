///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ci.prosuma.prosumagpv.entity.dto;
//
///**
// *
// * @author tagsergi
// */
//public class EnteteMouvementDTO {
//    
//    private long id;
//
//	private Date dateMouvement;
//
//	private String origineMouvement;
//
//	private String observations;
//
//	private String userCreation;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//        @Fetch(FetchMode.JOIN)
//	@JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK")
//	private PointDeVente pvt;
//
//	private String numFac;
//
//	private long enteteInv;
//
//	@Transient
//	private float montant;
//    
//}
