import { InventoryAdministrationPage } from './app.po';

describe('inventory-administration App', function() {
  let page: InventoryAdministrationPage;

  beforeEach(() => {
    page = new InventoryAdministrationPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
