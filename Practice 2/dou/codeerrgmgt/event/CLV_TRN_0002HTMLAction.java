package com.clt.apps.opus.dou.doutraining.codeerrgmgt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.dou.doutraining.codeerrgmgt.event.ClvTrn0002Event;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdDtlVO;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

/**
 * 
 * @author Nguyen Viet Lam
 *
 */
public class CLV_TRN_0002HTMLAction extends HTMLActionSupport {
	private static final long serialVersionUID = 1L;

	public CLV_TRN_0002HTMLAction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		ClvTrn0002Event event = new ClvTrn0002Event();
		IntgCdVO intgCdMsgVo = new IntgCdVO();
		IntgCdDtlVO intgCdDtlVO = new IntgCdDtlVO();
		if (command.isCommand(FormCommand.MULTI)) {
			event.setIntgCdVOs((IntgCdVO[]) getVOs(request, IntgCdVO.class, ""));
		} else if (command.isCommand(FormCommand.MULTI01)) {
			event.setIntgCdDtlVOs((IntgCdDtlVO[]) getVOs(request,
					IntgCdDtlVO.class, ""));
		} else if (command.isCommand(FormCommand.SEARCH)) {
			intgCdMsgVo.setOwnrSubSysCd(JSPUtil.getParameter(request,
					"s_ownr_sub_sys_cd", ""));
			intgCdMsgVo.setIntgCdId(JSPUtil.getParameter(request,
					"s_intg_cd_id", ""));
			event.setIntgCdVO(intgCdMsgVo);
		} else if (command.isCommand(FormCommand.SEARCH01)) {
			intgCdDtlVO.setIntgCdId(JSPUtil.getParameter(request,
					"s_intg_cd_id", ""));
			event.setIntgCdDtlVO(intgCdDtlVO);
		}
		request.setAttribute("Event", event);

		return event;
	}

	/**
 * 
 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);

	}

	/**
	 * 
	 * @param request
	 * @param event
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}

}