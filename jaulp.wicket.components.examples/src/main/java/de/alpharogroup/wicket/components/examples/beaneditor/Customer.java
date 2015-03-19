package de.alpharogroup.wicket.components.examples.beaneditor;

import java.io.Serializable;

import de.alpharogroup.wicket.components.examples.radios.Brands;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Customer.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements Serializable {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The name. */
  @Mandatory
  public String name = "";
  
  /** The car. */
  public Brands car = Brands.FERRARI;
  
  /** The premium. */
  public boolean premium;
}
