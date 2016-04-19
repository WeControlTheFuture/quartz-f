package org.quartz.ui.web.init;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.betwixt.io.BeanReader;
import org.quartz.ee.servlet.QuartzInitializerServlet;
import org.quartz.ui.web.action.base.BaseAction;
import org.quartz.ui.web.model.DefinitionManager;

/**
 * Definition extends QuartInitializerServlet by calling its super methods, but
 * also loading JobDefinitions into application context
 * 
 * @since Oct 2, 2003
 * @version $Revision: 59 $
 * @author Matthew Payne
 */

public class DefinitionInitializer extends QuartzInitializerServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5089452460410388309L;

	public static String DEFAULT_DEFINITION_FILE = "/JobDefinitions.xml";

	@Override
	public void init(ServletConfig cfg) throws ServletException {
		super.init(cfg);

		ServletContext context = cfg.getServletContext();
		String definitionPath = this.getInitParameter("definition-file");

		BeanReader beanReader = new BeanReader();

		// Configure the reader
		beanReader.getXMLIntrospector().setAttributesForPrimitives(false);

		if (definitionPath != null && definitionPath != "") {
			// Now we parse the xml
			try {
				// Register beans so that betwixt knows what the xml is to be
				// converted to

				beanReader.registerBeanClass("JobDefinitions", DefinitionManager.class);
				File defFile = new File(definitionPath);
				DefinitionManager defs = null;

				if (!defFile.exists()) {

					this.log("Alternate user definitions file, not specfic or does not exist.  Default resource /JobDefinitions.xml will be tried.");

					// defFile = new
					// File(context.getRealPath("/WEB-INF/JobDefinitions.xml"));
					this.log("Attempting to read definitions from file "
							+ this.getClass().getResource(DEFAULT_DEFINITION_FILE).getFile());

					URL url = this.getClass().getResource(DEFAULT_DEFINITION_FILE);

					if (url == null) {
						this.log("resource " + DEFAULT_DEFINITION_FILE + " not found");
					}

					defs = (DefinitionManager) beanReader.parse(url.toURI().toString());

				} else {
					this.log("Reading definitions from " + definitionPath);
				}

				if (defs != null) {
					context.setAttribute(BaseAction.JOB_DEFINITIONS_PROP, defs);
					log(defs.getDefinitions().size() + " Definition(s) loaded from config file");
				} else {
					log("no definitions found");

				}

			} catch (IntrospectionException e) {
				log("error reading definitions", e);

			} catch (IOException e) {
				log("IO error reading definitions", e);

			} catch (Exception e) {
				log("error reading definitions", e);
			}
		} else {
			log("Error definition-file init parameter not specified");
		}

	}

	@Override
	public void destroy() {
		this.getServletContext().setAttribute("Util.JOB_DEFINITIONS_PROP", null);
		super.destroy();

	}

}