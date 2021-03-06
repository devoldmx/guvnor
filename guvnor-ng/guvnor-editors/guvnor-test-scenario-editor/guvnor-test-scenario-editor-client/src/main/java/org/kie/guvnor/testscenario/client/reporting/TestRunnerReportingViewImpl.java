package org.kie.guvnor.testscenario.client.reporting;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.*;
import org.kie.guvnor.commons.ui.client.resources.CommonImages;
import org.kie.guvnor.testscenario.client.resources.i18n.TestScenarioConstants;
import org.kie.guvnor.testscenario.client.service.TestRuntimeReportingService;
import org.kie.guvnor.testscenario.model.Failure;

import javax.inject.Inject;

public class TestRunnerReportingViewImpl
        extends Composite
        implements TestRunnerReportingView,
        RequiresResize {

    interface Template extends SafeHtmlTemplates {
        @Template("<span style='color:{0}'>{1}</span>")
        SafeHtml title(String color, String text);
    }

    static final Template SUCCESS_TEMPLATE = GWT.create(Template.class);

    private static Binder uiBinder = GWT.create(Binder.class);
    private Presenter presenter;

    interface Binder extends UiBinder<Widget, TestRunnerReportingViewImpl> {

    }

    @UiField(provided = true)
    DataGrid<Failure> dataGrid;

    @UiField
    VerticalPanel panel;

    @UiField
    HTML successPanel;

    @UiField
    Label explanationLabel;


    @Inject
    public TestRunnerReportingViewImpl() {
        dataGrid = new DataGrid<Failure>();
        dataGrid.setWidth("100%");

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("---"));

        setUpColumns();

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void onResize() {
        dataGrid.setPixelSize((int) (getParent().getOffsetWidth() * 0.60),
                getParent().getOffsetHeight());
        dataGrid.onResize();
    }

    private void setUpColumns() {
        addSuccessColumn();
        addTextColumn();
    }

    private void addSuccessColumn() {
        Column<Failure, ImageResource> column = new Column<Failure, ImageResource>(new ImageResourceCell()) {
            @Override
            public ImageResource getValue(Failure failure) {
                presenter.onAddingFailure(failure);
                return CommonImages.INSTANCE.error();
            }
        };
        dataGrid.addColumn(column);
        dataGrid.setColumnWidth(column, 10, Style.Unit.PCT);
    }

    private void addTextColumn() {
        Column<Failure, String> column = new Column<Failure, String>(new ClickableTextCell()) {
            @Override
            public String getValue(Failure failure) {
                return failure.getDisplayName();
            }
        };

        column.setFieldUpdater(new FieldUpdater<Failure, String>() {
            @Override
            public void update(int index, Failure failure, String value) {
                presenter.onMessageSelected(failure);
            }
        });
        dataGrid.addColumn(column, TestScenarioConstants.INSTANCE.Text());
        dataGrid.setColumnWidth(column, 60, Style.Unit.PCT);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void bindDataGridToService(TestRuntimeReportingService testRuntimeReportingService) {
        testRuntimeReportingService.addDataDisplay(dataGrid);
    }

    @Override
    public void showSuccess() {
        successPanel.setHTML(SUCCESS_TEMPLATE.title("green", TestScenarioConstants.INSTANCE.Success()));
    }

    @Override
    public void showFailure() {
        successPanel.setHTML(SUCCESS_TEMPLATE.title("red", TestScenarioConstants.INSTANCE.ThereWereTestFailures()));
    }

    @Override
    public void setExplanation(String explanation) {
        explanationLabel.setText(explanation);
    }
}
