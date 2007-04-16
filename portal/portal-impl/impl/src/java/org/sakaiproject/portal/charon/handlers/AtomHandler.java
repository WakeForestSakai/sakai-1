/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007 The Sakai Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.portal.charon.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sakaiproject.portal.api.PortalHandlerException;
import org.sakaiproject.portal.api.PortalRenderContext;
import org.sakaiproject.tool.api.Session;

/**
 * Handler for Atom url space
 * 
 * @author ieb
 * @since Sakai 2.4
 * @version $Rev$
 * 
 */
public class AtomHandler extends BasePortalHandler
{
	public AtomHandler()
	{
		urlFragment = "atom";
	}

	@Override
	public int doGet(String[] parts, HttpServletRequest req, HttpServletResponse res,
			Session session) throws PortalHandlerException
	{
		if ((parts.length >= 2) && parts[1].equals("atom"))
		{
			try
			{

				// /portal/rss/site-id
				String siteId = null;
				if (parts.length >= 3)
				{
					siteId = parts[2];
				}

				PortalRenderContext rcontext = portal.includePortal(req, res, session,
						siteId,
						/* toolId */null, req.getContextPath() + req.getServletPath(),
						/* prefix */"site", /* doPages */true, /* resetTools */false,
						/* includeSummary */true, /* expandSite */false);
				// sendResponse(rcontext, res, parts[1],
				// "application/atom+xml");
				portal.sendResponse(rcontext, res, parts[1], "text/xml");
				return END;
			}
			catch (Exception ex)
			{
				throw new PortalHandlerException(ex);
			}
		}
		else
		{
			return NEXT;
		}
	}

}
