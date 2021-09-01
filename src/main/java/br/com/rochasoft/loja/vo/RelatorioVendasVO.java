package br.com.rochasoft.loja.vo;

import java.time.LocalDate;

public class RelatorioVendasVO 
{
	
	private String nomeProduto;
	private Long quantidadeVendida;
	private LocalDate dataUltimaVenda;
	
	public RelatorioVendasVO(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}
	public void setQuantidadeVendida(Long quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}
	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}
	public void setDataUltimaVenda(LocalDate dataUltimaVenda) {
		this.dataUltimaVenda = dataUltimaVenda;
	}
	@Override
	public String toString() {
		return "RelatorioVendasVO [nomeProduto=" + nomeProduto + ", quantidadeVendida=" + quantidadeVendida
				+ ", dataUltimaVenda=" + dataUltimaVenda + "]";
	}
	
	

}
