package org.jaulp.wicket.base.protocol.https;

import org.apache.wicket.protocol.https.RequireHttps;

/**
 * Implement this interface if the page should be accessible only with ssl(https).
 * 
 * @author Asterios Raptis
 */
@RequireHttps
public interface SSLable {
}