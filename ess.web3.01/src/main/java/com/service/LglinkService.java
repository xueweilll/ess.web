package com.service;

import java.util.List;

import com.hibernate.entity.Lglink;

public interface LglinkService {
	public List<Lglink> Lglinklist(int Gid);
 	public Lglink lglink(int id);
 	public void saveLglink(Lglink lglink);
 	public void updateLglink(Lglink lglink);
 	public void deleteLglink(int Gid);
 	public void deleteLglink(int Gid,int Lid);
}
